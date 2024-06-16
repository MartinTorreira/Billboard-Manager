package es.udc.paproject.backend.model.entities;

import javax.persistence.*;


@Entity
public class Movie {
    private Long id;
    private String title;
    private String summary;
    private int durationMinutes;

    public Movie(String title, String summary, int durationMinutes) {
        this.title = title;
        this.summary = summary;
        this.durationMinutes = durationMinutes;
    }

    public Movie() {}

    // getters y setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}