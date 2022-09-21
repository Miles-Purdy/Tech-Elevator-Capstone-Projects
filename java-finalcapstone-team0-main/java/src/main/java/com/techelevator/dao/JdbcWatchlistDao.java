package com.techelevator.dao;

import com.techelevator.model.UserChoice;
import com.techelevator.model.Watchlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcWatchlistDao implements WatchlistDao{
    private final JdbcTemplate jdbcTemplate;


    public JdbcWatchlistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Watchlist> getWatchlistMovies(int userId) {
        List<Watchlist> watchlistStuff = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM watchlist " +
                "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Watchlist watchlist = mapRowToWatchlist(results);
            watchlistStuff.add(watchlist);
        }
        return watchlistStuff;

    }

   @Override public boolean addToWatchlist(int userId, int movieId, String movieTitle, String posterPath) {
       String insertWatchlistSql = "insert into watchlist (user_id, movie_id, movie_title, poster_path) values (?,?, ?, ?)";

       return jdbcTemplate.update(insertWatchlistSql, userId, movieId, movieTitle, posterPath) == 1;

   }

    private Watchlist mapRowToWatchlist(SqlRowSet rs) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUserId(rs.getInt("user_id"));
        watchlist.setMovieId(rs.getInt("movie_id"));
        watchlist.setMovieTitle(rs.getString("movie_title"));
        watchlist.setPosterPath(rs.getString("poster_path"));
        return watchlist;
    }
}
