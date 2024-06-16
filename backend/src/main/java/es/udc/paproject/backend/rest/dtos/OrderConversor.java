package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Order;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConversor {

    private OrderConversor() {}

    public final static List<OrderSummaryDto> toOrderSummaryDtos(List<Order> orders) {
        return orders.stream().map(o -> toOrderSummaryDto(o)).collect(Collectors.toList());
    }

    public final static OrderSummaryDto toOrderSummaryDto(Order order) {

        return new OrderSummaryDto(order.getId(), order.getDate(),
                order.getSession().getMovie().getTitle(), order.getNumberOfTickets(),
                order.getTotalPrice(), order.getSession().getDateTime(),
                order.isDelivered());
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}
