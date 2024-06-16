package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.BillboardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BillboardServiceTest {

    @Autowired
    private BillboardService billboardService;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private SessionDao sessionDao;

    Movie movie1,movie2,movie3,movie4;
    Room room1;
    Session session1, session2,session3,session4,session5, session6;
    List<BillboardItem> billboard;
    BillboardItem item1,item2,item3,item4, item5;

/*
    // FUNC-2 VISUALIZAR PELICULA
    @Test
    public void getMovie() throws NonExistentMovieException {
        createMovies();
        assertEquals(movie1, billboardService.findMovie(movie1.getId()));
    }

    @Test
    public void getInvalidMovie(){
        assertThrows(NonExistentMovieException.class, ()-> billboardService.findMovie(42421L));
    }


    @Test
    public void getSession() throws NonExistentSessionException, SessionAlreadyStartedException {
        createMovies();
        createSessions(0);

        assertEquals(session1, billboardService.findSession(session1.getId()));
    }

    @Test
    public void getInvalidSession(){
        assertThrows(NonExistentSessionException.class, ()-> billboardService.findSession(42421L));
    }

    @Test
    public void alreadyStartedSession(){
        createMovies();
        createSessions(0);
        fillBillboard();
        assertThrows(SessionAlreadyStartedException.class, ()-> billboardService.findSession(session6.getId()));
    }

    @Test
    public void alphabeticalSortedMovies() throws IncorrectDateTime {
        createMovies();
        createSessions(0);
        fillBillboard();

        List<String> expected = Arrays.asList("Alien", "Blade Runner", "Matrix", "Titanic");
        List<String> stringList = new ArrayList<>();
        List <BillboardItem> billboardTest = billboardService.showBillboard(LocalDate.now());

        for (BillboardItem b : billboardTest){
            stringList.add(b.getMovie().getTitle());
        }

        assertEquals(expected, stringList);
    }

    @Test
    public void sessionsSortedByDates()throws IncorrectDateTime{
        createMovies();

        LocalDateTime t1 = LocalDateTime.now().plusMinutes(15);
        LocalDateTime t2 = LocalDateTime.now().plusMinutes(30);
        LocalDateTime t3 = LocalDateTime.now().plusMinutes(45);

        // Añadir fechas desordenadas
        Session s1 = new Session(movie1, room1, t2,200,10);
        sessionDao.save(s1);
        Session s2 = new Session(movie1, room1, t3, 200, 10);
        sessionDao.save(s2);
        Session s3 = new Session(movie1, room1, t1, 200, 10);
        sessionDao.save(s3);

        List<LocalDateTime> expected = Arrays.asList(t1,t2,t3);

        List <BillboardItem> testList = billboardService.showBillboard(LocalDate.now());
        List<Session> sessions = testList.get(0).getSessionList();

        List<LocalDateTime> dateList = new ArrayList<>();
        for (Session s : sessions){
            dateList.add(s.getDateTime());
        }

        assertEquals(expected,dateList);
    }

    @Test
    public void doNotShowStartedSessions()throws IncorrectDateTime{
        createMovies();

        LocalDateTime t1 = LocalDateTime.now().minusHours(1);
        LocalDateTime t2 = LocalDateTime.now().plusHours(1);

        //Started session
        session1 = new Session(movie1, room1, t1, 200, 20);
        sessionDao.save(session1);

        //Not started session
        session2 = new Session(movie1, room1, t2, 200, 20);
        sessionDao.save(session2);

        List<BillboardItem> testList = billboardService.showBillboard(LocalDate.now());

        assertEquals(t2,testList.get(0).getSessionList().get(0).getDateTime());
    }

    @Test
    public void showBillboardBetweenDates()throws IncorrectDateTime{
        createMovies();
        createSessions(1);
        fillBillboard();

        List<BillboardItem> billboard = billboardService.showBillboard(LocalDate.from(LocalDateTime.now().plusDays(1)));

        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

        assertEquals(tomorrow.getDayOfYear(), billboard.get(0).getSessionList().get(0).getDateTime().getDayOfYear());
        assertEquals(tomorrow.getDayOfYear(), billboard.get(1).getSessionList().get(0).getDateTime().getDayOfYear());
    }

    @Test
    public void incorrectDatetimeShowbillboard(){
        assertThrows(IncorrectDateTime.class, ()-> billboardService.showBillboard(LocalDate.from(LocalDateTime.now().plusDays(7))));
    }


    public void createMovies(){
        movie1 = new Movie("Titanic", "Se hunde un barco", 200);
        movieDao.save(movie1);

        movie2 = new Movie("Blade Runner", "Expolicía que encuentra y elimina a cuatro androides fugitivos", 20);
        movieDao.save(movie2);

        movie3 = new Movie("Alien", "La tripulación de una nave espacial es atacada por un extraterrestre", 20);
        movieDao.save(movie3);

        movie4 = new Movie("Matrix", "Un hombre descubre que la realidad es una simulación creada por máquinas", 20);
        movieDao.save(movie4);

        room1 = new Room("Room 1", 200);
        roomDao.save(room1);
    }

    public void createSessions(int day){
        session1 = new Session(movie1, room1, LocalDateTime.now().plusDays(day).plusMinutes(1), 200, 20);
        sessionDao.save(session1);

        session2 = new Session(movie2, room1, LocalDateTime.now().plusDays(day).plusMinutes(1), 120, 10);
        sessionDao.save(session2);

        session3 = new Session(movie3, room1, LocalDateTime.now().plusDays(day).plusMinutes(1), 150, 7);
        sessionDao.save(session3);

        session4 = new Session(movie4, room1, LocalDateTime.now().plusDays(day).plusMinutes(1), 190, 12);
        sessionDao.save(session4);

        session5 = new Session(movie1, room1, LocalDateTime.now().plusDays(day).plusMinutes(1), 200, 20);
        sessionDao.save(session5);

        session6 = new Session(movie1, room1, LocalDateTime.now().plusDays(day).minusMinutes(1), 50, 5);
        sessionDao.save(session6);

    }

    public void fillBillboard(){
        billboard = new ArrayList<>();

        item1 = new BillboardItem(movie1);
        item1.addSession(session1);
        item1.addSession(session5);

        item2 = new BillboardItem(movie2);
        item2.addSession(session2);

        item3 = new BillboardItem(movie3);
        item3.addSession(session3);

        item4 = new BillboardItem(movie4);
        item4.addSession(session4);
        item4.addSession(session6);

        billboard.add(item1);
        billboard.add(item2);
        billboard.add(item3);
        billboard.add(item4);
    }

 */
}
