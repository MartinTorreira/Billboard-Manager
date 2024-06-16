package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Session {
    private Long id;
    private Movie movie;
    private Room room;
    private LocalDateTime dateTime;
    private int availableTickets;
    private BigDecimal ticketPrice;

    private long version;

    public Session(LocalDateTime dateTime, int availableTickets, BigDecimal ticketPrice) {

        this.dateTime = dateTime;
        this.availableTickets = availableTickets;
        this.ticketPrice = ticketPrice;
    }

    public Session() {

    }

    public Session(Movie movie1, Room room1, LocalDateTime now, int avaiableTickets, BigDecimal ticketPrice) {
        this.movie = movie1;
        this.room = room1;
        this.dateTime = now;
        this.availableTickets = avaiableTickets;
        this.ticketPrice = ticketPrice;
        this.version = 0;
    }


    // getters y setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name= "movieId")
    public Movie getMovie() {
        return movie;
    }

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name= "roomId")
    public Room getRoom() {
        return room;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }


    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setRoom(Room room) {
        this.room = room;
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


    @Override
    public String toString() {
        return "Session {" +  dateTime.format(DateTimeFormatter.ofPattern("HH:mm")) +
                '}';
    }
}