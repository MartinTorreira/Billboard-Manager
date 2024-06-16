package es.udc.paproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    private Long roomId;
    private String roomName;
    private int capacity;

    public Room(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public Room() {

    }


    // getters y setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return roomId;
    }

    public void setId(Long roomId) {
        this.roomId = roomId;
    }
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
