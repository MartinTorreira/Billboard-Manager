package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.entities.Order;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ShoppingService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static es.udc.paproject.backend.rest.dtos.OrderConversor.*;


@RestController
@RequestMapping("/buying")
public class BuyingController {

    private final static String INCORRECT_QUANTITY_OF_TICKETS = "project.exceptions.IncorrectQuantityOfTickets";
    private final static String ALREADY_DELIVERED_TICKETS_EXCEPTION = "project.exceptions.AlreadyDeliveredException";
    private final static String NOT_MATCHING_CREDIT_CARD = "project.exceptions.IncorrectCreditCard";
    private final static String INCORRECT_CREDIT_CARD_INPUT = "project.exceptions.NotMatchingCreditCard";

    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(AlreadyDeliveredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleAlreadyDeliveredException(AlreadyDeliveredException exception, Locale locale){

        String errorMessage = messageSource.getMessage(NOT_MATCHING_CREDIT_CARD, null, exception.getLocalizedMessage(),
                locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(NotMatchingCreditCard.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleIncorrectCreditCard(NotMatchingCreditCard exception, Locale locale){

        String errorMessage = messageSource.getMessage(ALREADY_DELIVERED_TICKETS_EXCEPTION, null, exception.getLocalizedMessage(),
                locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(IncorrectQuantityOfTickets.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleIncorrectQuantityOfTickets(IncorrectQuantityOfTickets exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INCORRECT_QUANTITY_OF_TICKETS, null, exception.getMessage(),
                locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(IncorrectCreditCardInput.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleIncorrectCreditCardInputException(IncorrectCreditCardInput exception, Locale locale){

        String errorMessage = messageSource.getMessage(INCORRECT_CREDIT_CARD_INPUT, null, exception.getLocalizedMessage(),
                locale);

        return new ErrorsDto(errorMessage);
    }

    @GetMapping("/orders")
    public BlockDto<OrderSummaryDto> findOrders(@RequestAttribute Long userId,
                                                @RequestParam(defaultValue="0") int page) {

        Block<Order> orderBlock = shoppingService.findOrders(userId, page, 2);

        return new BlockDto<>(toOrderSummaryDtos(orderBlock.getItems()), orderBlock.getExistMoreItems());
    }

    @PostMapping("/buy/{sessionId}")
    public Long buy(@RequestAttribute Long userId, @PathVariable Long sessionId,
                                                          @Validated @RequestBody BuyParams params)
            throws IncorrectQuantityOfTickets, InstanceNotFoundException, SessionAlreadyStartedException, IncorrectCreditCardInput {

        return shoppingService.buy(userId, sessionId, params.getCreditCard(), params.getNumberOfTickets()).getId();
    }

    @PostMapping("/deliver")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deliverTickets(@RequestAttribute Long userId,
                                 @Validated @RequestBody DeliverTicketsParams params)
            throws PermissionException, SessionAlreadyStartedException, NotMatchingCreditCard, InstanceNotFoundException,
            AlreadyDeliveredException {

        shoppingService.deliverTickets(params.getOrderId(), params.getCreditCard());
    }

}
