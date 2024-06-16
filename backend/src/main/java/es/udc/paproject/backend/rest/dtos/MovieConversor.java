package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieConversor {

    private MovieConversor() {}

    public final static MovieDto toMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getSummary(),
                movie.getDurationMinutes());

    }
}
