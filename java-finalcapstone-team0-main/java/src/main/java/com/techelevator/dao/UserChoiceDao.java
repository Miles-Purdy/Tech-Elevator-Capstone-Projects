package com.techelevator.dao;

import com.techelevator.model.UserChoice;

import java.util.List;

public interface UserChoiceDao {

    UserChoice getChoiceById(int choiceId);

    boolean setChoice (int userId, int movieId, boolean liked );

    List<UserChoice> findLikedById(int userId);

    List<UserChoice> findDislikedById(int userId);

    int getRandomMovie(int userId);

}
