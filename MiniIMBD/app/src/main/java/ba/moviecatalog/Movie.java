package ba.moviecatalog;

import java.io.Serializable;

// Klasa koja predstavlja jedan film
public class Movie implements Serializable {   // ➝ dodali implements Serializable
    private int imageResId;
    private String title;
    private String genre;
    private String description;
    private float rating;
    private Actor[] actors;
    private boolean favorit;

    public Movie(int imageResId, String title, String genre, String description, float rating, Actor[] actors) {
        this.imageResId = imageResId;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.actors = actors;
        this.favorit = false;
    }

    public int getImageResId() { return imageResId; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getDescription() { return description; }
    public float getRating() { return rating; }
    public Actor[] getActors() { return actors; }

    public boolean isFavorit() { return favorit; }
    public void setFavorit(boolean favorit) { this.favorit = favorit; }
}
