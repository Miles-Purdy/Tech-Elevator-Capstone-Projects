package com.techelevator.model;

public class GenrePreferences {
    private int userId;
    private int genreId;
    private int[] genres;

    public GenrePreferences(int userId, int[] genres) {
        this.userId = userId;
        this.genres = genres;
    }

    public GenrePreferences() {
    }

    public int getUserId() {
        return userId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int[] getGenres() {
        return genres;
    }
}
