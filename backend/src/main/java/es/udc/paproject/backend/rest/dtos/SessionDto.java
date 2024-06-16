package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Room;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionDto {

    private Long id;
    private Long movieId;



    private String movieTitle;
    private int duration;
    private String roomName;
    private Long roomId;
    private LocalDateTime dateTime;
    private int availableTickets;
    private BigDecimal ticketPrice;


    public SessionDto(Long id, Long movie1, String movieName, int duration, Long room1, String roomName, LocalDateTime now,
                      int avaiableTickets, BigDecimal ticketPrice) {
        this.id = id;
        this.movieId = movie1;
        this.roomId = room1;
        this.dateTime = now;
        this.availableTickets = avaiableTickets;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieName;
        this.duration = duration;
        this.roomName = roomName;
    }


    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Long getRoomId() {
        return roomId;
    }


    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
