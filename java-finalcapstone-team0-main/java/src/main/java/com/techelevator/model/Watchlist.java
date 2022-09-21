package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

    public class Watchlist {

        private int userId;
        private int movieId;
        private String movieTitle;
        private String posterPath;

        public String getMovieTitle() {
            return movieTitle;
        }

        public void setMovieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public Watchlist(int userId, int movieId, String movieTitle, String posterPath) {
            this.userId = userId;
            this.movieId = movieId;
            this.movieTitle = movieTitle;
            this.posterPath = posterPath;
        }

        public Watchlist(){

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
    }