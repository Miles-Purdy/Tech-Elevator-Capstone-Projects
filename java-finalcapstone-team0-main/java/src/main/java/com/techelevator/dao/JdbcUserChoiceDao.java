package com.techelevator.dao;


import com.techelevator.model.User;
import com.techelevator.model.UserChoice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcUserChoiceDao implements UserChoiceDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserChoiceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserChoice getChoiceById(int choiceId){
        UserChoice choice = new UserChoice();

        String sql = "SELECT * " +
                "FROM user_preferences " +
                "WHERE choice_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, choiceId);
        if (results.next()) {
            choice = mapRowToUserChoice(results);
        }
        return choice;
    }



    @Override
    public boolean setChoice (int userId, int movieId, boolean liked){
        String insertUserChoiceSql = "insert into user_preferences (user_id, movie_id, liked) values (?,?,?)";

        return jdbcTemplate.update(insertUserChoiceSql, userId, movieId, liked) == 1;
    }


    @Override
    public List<UserChoice> findLikedById(int userId){
        List<UserChoice> userChoices = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM user_preferences " +
                "WHERE user_id = ? AND liked = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            UserChoice userChoice = mapRowToUserChoice(results);
            userChoices.add(userChoice);
        }

        return userChoices;
    }


    @Override
    public List<UserChoice> findDislikedById(int userId){
        List<UserChoice> userChoices = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM user_preferences " +
                "WHERE user_id = ? AND liked = false";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            UserChoice userChoice = mapRowToUserChoice(results);
            userChoices.add(userChoice);
        }

        return userChoices;
    }

    @Override
    public int getRandomMovie(int userId) {
        UserChoice choice = new UserChoice();

       String sql = "SELECT movie_id " +
               "FROM user_preferences " +
               "WHERE user_id = ? AND liked = 'true' " +
               "ORDER BY RANDOM () LIMIT 1;";

       int movieId = jdbcTemplate.queryForObject(sql, Integer.class, userId );

       return movieId;
    }

    private UserChoice mapRowToUserChoice(SqlRowSet rs) {
        UserChoice userChoice = new UserChoice();
        userChoice.setUserId(rs.getInt("user_id"));
        userChoice.setMovieId(rs.getInt("movie_id"));
        userChoice.setLiked(rs.getBoolean("liked"));
        return userChoice;
    }
}
