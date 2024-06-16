package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Room;

public class RoomDto {
    private Long roomId;
    private String roomName;
    private int capacity;

    public RoomDto() {}
    public RoomDto(Long roomId, String roomName, int capacity){
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
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
