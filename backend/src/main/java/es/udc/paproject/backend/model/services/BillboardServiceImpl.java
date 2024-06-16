package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@Transactional(readOnly=true)
public class BillboardServiceImpl implements BillboardService{

    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private MovieDao movieDao;

    @Override
    public List<BillboardItem> showBillboard(LocalDate dateTime) throws IncorrectDateTime {

        dateTime = Objects.requireNonNullElseGet(dateTime, LocalDate::now);
        LocalDateTime date;

        if (dateTime.isBefore(LocalDate.now()) || dateTime.isAfter(LocalDate.now().plusDays(6)))
            throw new IncorrectDateTime("Error: Fecha incorrecta");

        if(dateTime.equals(LocalDate.now())){
            date = LocalDateTime.now();
        }else{
            date = dateTime.atTime(0,0,0,0);
        }

        BillboardItem item = new BillboardItem(new Movie("","",0));
        List<BillboardItem> billboard = new ArrayList<>();

        List<Session> sessions = sessionDao.findSessionByDateTimeBetweenOrderByMovieTitleAscDateTimeAsc(date,
                date.truncatedTo(ChronoUnit.DAYS).plusDays(1).minusMinutes(1));

        for(Session session : sessions){
            if (! item.getMovie().getTitle().equals(session.getMovie().getTitle())){
                item = new BillboardItem(session.getMovie());
                billboard.add(item);
            }

            item.addSession(session);
        }

        return billboard;
    }

    @Override
    public Session findSession(Long sessionId) throws InstanceNotFoundException, SessionAlreadyStartedException {
        Optional<Session> optionalSession = sessionDao.findById(sessionId);
        Session session;

        if(optionalSession.isEmpty()){
            throw new InstanceNotFoundException("project.entities.session", sessionId);
        }
        session = optionalSession.get();

        if(session.getDateTime().isBefore(LocalDateTime.now())){
            throw new SessionAlreadyStartedException("Sesión ya comenzada");
        }
        return session;
    }

    @Override
    @Transactional(readOnly=true)
    public Movie findMovie(Long movieId) throws InstanceNotFoundException {
        Optional<Movie> optionalMovie = movieDao.findById(movieId);
        Movie movie;

        if(optionalMovie.isEmpty()){
            throw new InstanceNotFoundException("project.entities.movie", movieId);
        }
        movie = optionalMovie.get();
        return movie;
    }
}
