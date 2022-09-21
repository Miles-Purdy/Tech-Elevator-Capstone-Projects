package com.techelevator.dao;

import com.techelevator.model.Watchlist;

import java.util.List;

public interface WatchlistDao {

    List<Watchlist> getWatchlistMovies(int userId);

    boolean addToWatchlist(int userId, int movieId, String movieTitle, String posterPath);

}
