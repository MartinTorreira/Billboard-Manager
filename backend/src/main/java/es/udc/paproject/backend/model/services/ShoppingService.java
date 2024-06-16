package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Order;
import es.udc.paproject.backend.model.exceptions.*;

public interface ShoppingService {

    Order buy(Long userId, Long sessionId, String CreditCard, int numberOfTickets) throws InstanceNotFoundException, IncorrectQuantityOfTickets, SessionAlreadyStartedException;
    void deliverTickets(Long orderId, String creditCard) throws InstanceNotFoundException, SessionAlreadyStartedException, AlreadyDeliveredException, NotMatchingCreditCard;
    Block<Order> findOrders(Long userId, int page, int size);

}
