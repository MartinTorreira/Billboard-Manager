package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ShoppingService;
import es.udc.paproject.backend.model.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ShoppingTest {

    @Autowired
    private UserService userService;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ShoppingService shoppingService;

    private final String CREDIT_CARD_1 = "1111222233334444";
    private final String CREDIT_CARD_2 = "5555666677778888";

/*
    private User signUpUser(String userName) {
        User user = new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");

        try {
            userService.signUp(user);
        } catch (DuplicateInstanceException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    // --- FUNC 4 comprar entradas (FUNC-4)
    @Test
    public void buyTickets() throws InstanceNotFoundException, IncorrectQuantityOfTickets, PermissionException, SessionAlreadyStartedException, IncorrectCreditCardInput {
        User user = signUpUser("user");
        List<Session> sessions = createInfo();
        Order order2 = null;

        Order order1 = shoppingService.buy(user.getId(), sessions.get(0).getId(), CREDIT_CARD_1, 1);
        Optional<Order> foundOrder = orderDao.findById(order1.getId());
        if(foundOrder.isPresent()){
            order2 = foundOrder.get();
        }
        assertEquals(order1, order2);
    }
    @Test
    public void buyWithNotAvailableTickets() {
        User user = signUpUser("user");

        List<Session> sessions = createInfo();

        assertThrows(IncorrectQuantityOfTickets.class, () ->
                shoppingService.buy(user.getId(), sessions.get(0).getId(), CREDIT_CARD_1, 300));
    }

    @Test
    public void buyWithIncorrectSessionId() {
        User user = signUpUser("user");

        assertThrows(InstanceNotFoundException.class, () ->
                shoppingService.buy(user.getId(), 543543L, CREDIT_CARD_1, 300));
    }

    @Test
    public void buyWithIncorrectUserId() {
        List<Session> sessions = createInfo();

        assertThrows(InstanceNotFoundException.class, () ->
                shoppingService.buy(453453454L, sessions.get(0).getId(), CREDIT_CARD_1, 300));
    }


    // --- FUNC 5 TEST (VISUALIZAR HISTORICO DE COMPRAS (FUNC-5) //IMPLEMENTAR MAS CASOS DE USO
    @Test
    public void buyAndFindOrders() throws InstanceNotFoundException, IncorrectQuantityOfTickets, SessionAlreadyStartedException, IncorrectCreditCardInput {
        //Create and login as user
        User user = signUpUser("user");

        List<Session> sessions = createInfo();

        Order order1 = shoppingService.buy(user.getId(), sessions.get(0).getId(), "1200180018001245", 1);
        Order order2 = shoppingService.buy(user.getId(), sessions.get(1).getId(), "1400180018001245", 3);
        Order order3 = shoppingService.buy(user.getId(), sessions.get(2).getId(), "1600180018001245", 5);
        Order order4 = shoppingService.buy(user.getId(), sessions.get(3).getId(), "1800180018001245", 7);

        Block<Order> expectedBlock = new Block<>(Arrays.asList(order1, order2), true);
        assertEquals(expectedBlock, shoppingService.findOrders(user.getId(), 0, 2));

        expectedBlock = new Block<>(Arrays.asList(order3, order4), false);
        assertEquals(expectedBlock, shoppingService.findOrders(user.getId(), 1, 2));
    }

    @Test
    public void testFindNoOrders() {

        User user = signUpUser("user");
        Block<Order> expectedOrders = new Block<>(new ArrayList<>(), false);

        assertEquals(expectedOrders, shoppingService.findOrders(user.getId(), 0, 1));

    }

    // FUNC 6 TEST(ENTREGAR LAS ENTREDAS DE UNA COMPRA (FUNC-6)
    @Test
    public void deliverTickets() throws InstanceNotFoundException,
            SessionAlreadyStartedException, NotMatchingCreditCard, AlreadyDeliveredException, PermissionException,
            IncorrectQuantityOfTickets, IncorrectCreditCardInput {
        User user = signUpUser("user");
        List<Session> sessions = createInfo();

        Order order1 = shoppingService.buy(user.getId(), sessions.get(0).getId(), CREDIT_CARD_1, 1);

        shoppingService.deliverTickets(order1.getId(), CREDIT_CARD_1);

        Order foundOrder = orderDao.findByIdAndCreditCard(order1.getId(), CREDIT_CARD_1);

        assertTrue(foundOrder.isDelivered());
    }



    @Test
    public void deliverTicketsNotMatchingCreditCard() throws InstanceNotFoundException, IncorrectQuantityOfTickets,
            SessionAlreadyStartedException, IncorrectCreditCardInput {
        User user = signUpUser("user");
        List<Session> sessions = createInfo();

        Order order1 = shoppingService.buy(user.getId(), sessions.get(0).getId(), CREDIT_CARD_1, 1);

        assertThrows(NotMatchingCreditCard.class, () ->
                shoppingService.deliverTickets(order1.getId(), CREDIT_CARD_2));

    }

    @Test
    public void deliverTicketsAlreadyDelivered() throws InstanceNotFoundException, IncorrectQuantityOfTickets,
            SessionAlreadyStartedException, IncorrectCreditCardInput {
        User user = signUpUser("user");
        List<Session> sessions = createInfo();

        Order order1 = shoppingService.buy(user.getId(), sessions.get(0).getId(), CREDIT_CARD_1, 1);

        order1.setDelivered(true);
        assertThrows(AlreadyDeliveredException.class, () ->
                shoppingService.deliverTickets(order1.getId(), CREDIT_CARD_2));
    }

    @Test
    public void deliverTicketsIncorrectOrderId() {
        User user = signUpUser("user");

        assertThrows(InstanceNotFoundException.class, () ->
                shoppingService.deliverTickets(553423L, CREDIT_CARD_1));
    }

    @Test
    public void incorrectCreditCardInput() {
        User user = signUpUser("user");

        List<Session> sessions = createInfo();

        assertThrows(IncorrectCreditCardInput.class, () ->
                shoppingService.buy(user.getId(), sessions.get(0).getId(), "111122223333444A", 1));

    }


    //CREATE MOVIES, ROOMS AND SESSIONS
    public List<Session> createInfo(){
        Movie movie1 = new Movie("Titanic", "Se hunde un barco", 200);
        movieDao.save(movie1);
        Movie movie2 = new Movie("Blade Runner", "Expolicía que encuentra y elimina a cuatro androides fugitivos", 117);
        movieDao.save(movie2);
        Movie movie3 = new Movie("Alien", "La tripulación de una nave espacial es atacada por un extraterrestre", 112);
        movieDao.save(movie3);
        Movie movie4 = new Movie("Matrix", "Un hombre descubre que la realidad es una simulación creada por máquinas", 114);
        movieDao.save(movie4);
        Room room1 = new Room("Room 1", 200);
        roomDao.save(room1);

        //Sessions
        Session session1 = new Session(movie1, room1, LocalDateTime.now().plusMinutes(10), 200, 20);
        sessionDao.save(session1);
        Session session2 = new Session(movie2, room1, LocalDateTime.now().plusMinutes(20), 120, 10);
        sessionDao.save(session2);
        Session session3 = new Session(movie3, room1, LocalDateTime.now().plusMinutes(30), 150, 7);
        sessionDao.save(session3);
        Session session4 = new Session(movie4, room1, LocalDateTime.now().plusMinutes(40), 190, 12);
        sessionDao.save(session4);

        Session session5 = new Session(movie4, room1, LocalDateTime.now().minusMinutes(20), 190, 12);
        sessionDao.save(session5);


        List<Session> sessions = new ArrayList<>();
        sessions.add(session1);sessions.add(session2);
        sessions.add(session3);sessions.add(session4);
        sessions.add(session5);

        return sessions;
    }

*/
}
