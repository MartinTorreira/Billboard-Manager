package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface SessionDao extends PagingAndSortingRepository<Session, Long> {
    List<Session> findSessionByDateTimeBetweenOrderByMovieTitleAscDateTimeAsc(LocalDateTime startTime, LocalDateTime endTime);
}
