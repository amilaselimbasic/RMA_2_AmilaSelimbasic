package com.example.gdjedanas.models;

import java.io.Serializable;

public class Event implements Serializable {

    private String id;
    private int image;
    private String title;
    private String city;
    private String place;
    private String date;
    private String time;
    private String description;
    private float rating;
    private int reviews;
    private String category;
    private boolean favorite;

    public Event() {}

    public Event(int image, String title, String city, String place,
                 String date, String time, String description,
                 float rating, int reviews, String category) {

        this.id = title + date;
        this.image = image;
        this.title = title;
        this.city = city;
        this.place = place;
        this.date = date;
        this.time = time;
        this.description = description;
        this.rating = rating;
        this.reviews = reviews;
        this.category = category;
        this.favorite = false;
    }

    public String getId() { return id; }
    public int getImage() { return image; }
    public String getTitle() { return title; }
    public String getCity() { return city; }
    public String getPlace() { return place; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getDescription() { return description; }
    public float getRating() { return rating; }
    public int getReviews() { return reviews; }
    public String getCategory() { return category; }

    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;
        return id != null && id.equals(event.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}