package com.liguagensalura.demo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "principaisLinguagens")
public class Linguagem {
    @Id
    private String id;
    private String title;
    private String image;
    private double rating;

    private int votes;

    public Linguagem(){ }

    public Linguagem(String title, String image, int rating) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Linguagem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating + '\'' +
                ", votes" + votes +
                '}';
    }
}