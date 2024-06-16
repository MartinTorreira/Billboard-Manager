package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SessionDateDto {

    private Long id;
    private LocalDateTime dateTime;

    public SessionDateDto(Long id,  LocalDateTime now) {
        this.id = id;
        this.dateTime = now;
    }


    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


}
