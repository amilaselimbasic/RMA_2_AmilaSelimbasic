package com.example.gdjedanas.models;

public class Event {

    private int image;

    private String title;

    private String city;

    private String place;

    private String date;

    private String time;

    private String description;

    private float rating;

    private int reviews;


    public Event(int image,
                 String title,
                 String city,
                 String place,
                 String date,
                 String time,
                 String description,
                 float rating,
                 int reviews) {

        this.image = image;
        this.title = title;
        this.city = city;
        this.place = place;
        this.date = date;
        this.time = time;
        this.description = description;
        this.rating = rating;
        this.reviews = reviews;
    }


    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }

    public int getReviews() {
        return reviews;
    }
}