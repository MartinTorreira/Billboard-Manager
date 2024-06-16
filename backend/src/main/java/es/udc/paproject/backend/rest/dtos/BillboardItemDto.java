package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;

import java.util.ArrayList;
import java.util.List;

public class BillboardItemDto {

    private Long id;
    private String movieTitle;
    private Long movieId;
    private List<SessionDateDto> sessionList = new ArrayList<>();

    public BillboardItemDto(){}

    public BillboardItemDto(Long id, Long movieId, String movieTitle, List<SessionDateDto> sessionList) {
        this.id = id;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.sessionList = sessionList;
    }

    public List<SessionDateDto> getSessionList() {return sessionList;}
    public void setSessionList(List<SessionDateDto> sessionList){this.sessionList = sessionList;}
    public void addSession(SessionDateDto session){
        sessionList.add(session);
    }


    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

}
