package com.techelevator.dao;

import com.techelevator.model.GenrePreferences;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGenrePreferencesDao implements GenrePreferencesDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGenrePreferencesDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GenrePreferences> getGenresByUserId(int userId) {
        List<GenrePreferences> pref = new ArrayList<>();
        String sql = "SELECT user_id, genre_id FROM users_genre_preferences WHERE user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            pref.add(mapRowToGenrePreferences(results));
        }
        return pref;
    }

    @Override
    public void addGenresToUserPref(GenrePreferences preferences) {
        String sqlDelete = "DELETE FROM users_genre_preferences WHERE user_id = ?;";
        jdbcTemplate.update(sqlDelete, preferences.getUserId());

        int[] genres = preferences.getGenres();
        String sql = "INSERT INTO users_genre_preferences (user_id, genre_id) " +
                "VALUES (?, ?)";

        for (int genreId : genres) {
            jdbcTemplate.update(sql, preferences.getUserId(), genreId);
        }
    }
    //  Works if you post body below to Postman with unique genreIds
    //{
    //        "userId": "3",
    //        "genres": [10751, 14]
    //}

    private GenrePreferences mapRowToGenrePreferences(SqlRowSet rs) {
        GenrePreferences pref = new GenrePreferences();
        pref.setUserId(rs.getInt("user_id"));
        pref.setGenreId(rs.getInt("genre_id"));
        return pref;
    }
}
