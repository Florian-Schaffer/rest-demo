package dtos;

import entities.Movie;

public class DTOMovie {
    private int year;
    private String title;
    private String[] actors;


    public DTOMovie(){

    }

    public DTOMovie(Movie movie){
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.actors = movie.getActors();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
}
