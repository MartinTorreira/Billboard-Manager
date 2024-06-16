package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Session;

import java.util.List;
import java.util.stream.Collectors;

public class SessionConversor {

    private SessionConversor() {}

    public final static SessionDto toSessionDto(Session session) {

        return new SessionDto(session.getId(), session.getMovie().getId(),session.getMovie().getTitle(),
                session.getMovie().getDurationMinutes(), session.getRoom().getId(),session.getRoom().getRoomName(),
                session.getDateTime(), session.getAvailableTickets(), session.getTicketPrice());

    }

    public final static SessionDateDto toSessionDateDto(Session session) {

        return new SessionDateDto(session.getId(), session.getDateTime());

    }
}
