package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateSessionAvailableTicketsDto {

    private Long sessionId;
    private int availableTickets;

    @NotNull
    public Long getSessiontId() {
        return sessionId;
    }

    public void setSessiontId(Long sessiontId) {
        this.sessionId = sessiontId;
    }

    @Min(value=1)
    public int setAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
}
