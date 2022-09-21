package com.techelevator.controller;


import com.techelevator.dao.UserChoiceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import com.techelevator.model.UserAlreadyExistsException;
import com.techelevator.model.UserChoice;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class ChoiceController {

    private UserChoiceDao userChoiceDao;

    private UserDao userDao;

    public ChoiceController(UserChoiceDao userChoiceDao) {
        this.userChoiceDao = userChoiceDao;
    }

    @RequestMapping(value = "/choice", method = RequestMethod.POST)
        public void choice(@Valid @RequestBody UserChoice newChoice) {

            userChoiceDao.setChoice(newChoice.getUserId(),newChoice.getMovieId(), newChoice.isLiked());
        }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public int randomMovieId(@PathVariable("id") int userId) {
        return userChoiceDao.getRandomMovie(userId);
    }

}
