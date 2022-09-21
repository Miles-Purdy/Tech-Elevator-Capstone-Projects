package com.techelevator.controller;


import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.PUT)
    public void giveUserAdminStatus(@Valid @RequestBody User userToUpdate) {

        userDao.assignAdminStatus(userToUpdate.getUsername());
    }
}
