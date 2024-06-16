package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.exceptions.IncorrectCreditCardInput;
import es.udc.paproject.backend.model.exceptions.IncorrectQuantityOfTickets;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BuyParams {

    private String creditCard;
    private int numberOfTickets;

    @Size(min=16, max=16)
    public String getCreditCard(){
        return creditCard;
    }

    public void setCreditCard(String creditCard){
        this.creditCard = creditCard;
    }


    public void setNumberOfTickets(int numberOfTickets){
        this.numberOfTickets = numberOfTickets;
    }

    @Min(value=1, message="debe de ser mayor o igual que 1")
    @Max(value=10, message="debe ser menor o igual que 10")
    public int getNumberOfTickets(){
        return this.numberOfTickets;
    }
    
}
