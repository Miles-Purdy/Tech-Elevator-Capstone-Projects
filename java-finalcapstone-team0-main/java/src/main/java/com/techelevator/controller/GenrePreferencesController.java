package com.techelevator.controller;

import com.techelevator.dao.GenrePreferencesDao;
import com.techelevator.model.GenrePreferences;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@Repository
public class GenrePreferencesController {
    private final GenrePreferencesDao genrePreferencesDao;

    public GenrePreferencesController(GenrePreferencesDao genrePreferencesDao) {
        this.genrePreferencesDao = genrePreferencesDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public List<GenrePreferences> getGenresByUserId(@PathVariable int userId) {
        return genrePreferencesDao.getGenresByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public void postGenresToUser(@Valid @RequestBody GenrePreferences preferences) {
        genrePreferencesDao.addGenresToUserPref(preferences);
    }

}

