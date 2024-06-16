package es.udc.paproject.backend.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillboardItem {

    private Long id;
    private Movie movie;
    private List<Session> sessionList = new ArrayList<>();

    public BillboardItem(){}

    public BillboardItem(Movie movie) {this.movie = movie;}

    public BillboardItem(Long id, Movie movie, List<Session> sessionList){
        this.id = id;
        this.movie = movie;
        this.sessionList = sessionList;
    }

    public List<Session> getSessionList() {return sessionList;}

    public void setSessionList(List<Session> sessionList){this.sessionList = sessionList;}

    public void addSession(Session session){
        sessionList.add(session);
    }


    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillboardItem that = (BillboardItem) o;
        return Objects.equals(movie, that.movie) && Objects.equals(sessionList, that.sessionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, sessionList);
    }


}
