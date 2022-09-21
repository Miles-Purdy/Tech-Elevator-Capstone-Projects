package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.WatchlistDao;
import com.techelevator.model.User;
import com.techelevator.model.Watchlist;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
public class WatchlistController {

        private WatchlistDao watchlistDao;

        public WatchlistController(WatchlistDao watchlistDao) {
                this.watchlistDao = watchlistDao;
        }

        @RequestMapping(value = "/watchlist", method = RequestMethod.POST)
        public void addToWatchlist(@Valid @RequestBody Watchlist watchlist) {

            watchlistDao.addToWatchlist(watchlist.getUserId(), watchlist.getMovieId(), watchlist.getMovieTitle(), watchlist.getPosterPath());
        }

    @RequestMapping(value = "/watchlist/{user_id}", method = RequestMethod.GET)
    public List<Watchlist> getWatchlistMovie(@PathVariable("user_id") int userId) {
        return watchlistDao.getWatchlistMovies(userId);
    }

}


