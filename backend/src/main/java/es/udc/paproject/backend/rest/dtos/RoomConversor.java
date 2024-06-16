package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomConversor {

    private RoomConversor() {}
    public final static RoomDto toRoomDto(Room room) {
        return new RoomDto(room.getId(), room.getRoomName(), room.getCapacity());
    }

    public final static List<RoomDto> toRoomDtos(List<Room> rooms) {
        return rooms.stream().map(p -> toRoomDto(p)).collect(Collectors.toList());
    }
}
