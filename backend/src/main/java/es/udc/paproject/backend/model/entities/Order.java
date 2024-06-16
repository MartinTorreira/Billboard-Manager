package es.udc.paproject.backend.model.entities;


import es.udc.paproject.backend.model.exceptions.IncorrectQuantityOfTickets;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="OrderTable")
public class Order {

    public static final int MAX_ITEMS = 10;
    public static final int MIN_ITEMS = 0;
    private Long id;
    private User user;
    private Session session;
    private LocalDateTime date;
    private String creditCard;
    private int numberOfTickets;

    private boolean delivered;

    public Order() {}

    public Order(User user, Session session, LocalDateTime now, String creditCard) {
        this.user = user;
        this.session = session;
        this.date = now;
        this.creditCard = creditCard;
        this.delivered = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name= "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name= "sessionId")
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard){
        this.creditCard = creditCard;
    }

    public int getNumberOfTickets() {return numberOfTickets;}

    public void setNumberOfTickets(int numberOfTickets) throws IncorrectQuantityOfTickets {
        if(numberOfTickets > MAX_ITEMS || numberOfTickets <= MIN_ITEMS){
            throw new IncorrectQuantityOfTickets("Numero de tickets incorrecto: " + numberOfTickets);
        }

        this.numberOfTickets = numberOfTickets;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
    @Transient
    public double getTotalPrice() {
        return numberOfTickets * session.getTicketPrice().intValue();
    }



}
