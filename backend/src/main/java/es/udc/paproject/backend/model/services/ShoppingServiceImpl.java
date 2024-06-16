package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService{

    @Autowired
    private PermissionChecker permissionChecker;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private UserDao userDao;


    @Override
    public Order buy(Long userId, Long sessionId, String creditCard, int numberOfTickets)
            throws InstanceNotFoundException, IncorrectQuantityOfTickets, SessionAlreadyStartedException {

        Session s;
        User u;
        Optional<Session> session = sessionDao.findById(sessionId);
        Optional<User>  user = userDao.findById(userId);

        if(session.isEmpty()){
            throw new InstanceNotFoundException("project.entities.session", sessionId);
        }
        if(user.isEmpty()){
            throw new InstanceNotFoundException("project.entities.user", userId);
        }

        s = session.get();
        u = user.get();

        Order order = new Order(u, s, LocalDateTime.now(), creditCard);
        order.setNumberOfTickets(numberOfTickets);


        if(s.getAvailableTickets() < numberOfTickets){
            throw new IncorrectQuantityOfTickets("No quedan entradas suficientes");
        }

        if(s.getDateTime().isBefore(LocalDateTime.now())){
            throw new SessionAlreadyStartedException("Sesión comenzada");
        }

        if(numberOfTickets >= 10)
            throw new IncorrectQuantityOfTickets("El límite de compra son diez entradas");

        s.setAvailableTickets(s.getAvailableTickets() - numberOfTickets);

        sessionDao.save(s);
        orderDao.save(order);

        return order;
    }

    public void deliverTickets(Long orderId, String creditCard)
            throws InstanceNotFoundException, SessionAlreadyStartedException, AlreadyDeliveredException,
            NotMatchingCreditCard {


        LocalDateTime now = LocalDateTime.now();
        Optional<Order> o = orderDao.findById(orderId);
        Order order;

        if(o.isEmpty()){
            throw new InstanceNotFoundException("compra", orderId);
        }

        order = o.get();

        if(order.isDelivered()){
            throw new AlreadyDeliveredException("Las entradas ya han sido entregadas");
        }
        if(!now.isBefore(order.getSession().getDateTime())){
            throw new SessionAlreadyStartedException("Sesión comenzada");
        }
        if(!Objects.equals(creditCard, order.getCreditCard())){
            throw new NotMatchingCreditCard("La tarjeta de crédito no es correcta");
        }

        order.setDelivered(true);
        orderDao.save(order);
    }


    @Override
    @Transactional(readOnly=true)
    public Block<Order> findOrders(Long userId, int page, int size) {
        Slice<Order> slice = orderDao.findByUserIdOrderByIdDesc(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }
}
