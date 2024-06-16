package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderSummaryDto {
    private Long id;
    private LocalDateTime date;
    private String movieTitle;
    private int numberOfTickets;
    private double totalPrice;
    private LocalDateTime sessionDate;
    private boolean isDelivered;

    public OrderSummaryDto() {}

    public OrderSummaryDto(Long id, LocalDateTime date, String movieTitle,
                           int numberOfTickets, double totalPrice, LocalDateTime sessionDate, boolean isDelivered) {
        this.id = id;
        this.date = date;
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
        this.sessionDate = sessionDate;
        this.isDelivered = isDelivered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

}
