package com.techelevator.model;

public class UserChoice {

    private int userId;

    private int movieId;

    private boolean liked;


    public UserChoice(){};



    public UserChoice(int userId, int movieId, boolean liked) {
        this.userId = userId;
        this.movieId = movieId;
        this.liked = liked;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

}
