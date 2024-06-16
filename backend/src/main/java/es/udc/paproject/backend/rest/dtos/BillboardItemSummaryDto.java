package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Movie;

public class BillboardItemSummaryDto {

    private Long id;
    private Movie movie;

    public BillboardItemSummaryDto(){}

    public BillboardItemSummaryDto(Long id, Movie movie) {
        this.id = id;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
