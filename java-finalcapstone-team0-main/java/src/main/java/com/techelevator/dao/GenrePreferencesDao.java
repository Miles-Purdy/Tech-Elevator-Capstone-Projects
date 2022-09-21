package com.techelevator.dao;

import com.techelevator.model.GenrePreferences;

import java.util.List;

public interface GenrePreferencesDao {

    List<GenrePreferences> getGenresByUserId(int userId);

    void addGenresToUserPref(GenrePreferences preferences);
}
