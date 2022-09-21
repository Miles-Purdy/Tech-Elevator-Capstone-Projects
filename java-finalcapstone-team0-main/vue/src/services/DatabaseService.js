import axios from 'axios';

// Inserted local host connection
const http = axios.create({
  baseURL: "http://localhost:9000"});

export default {

  setPreferences(preferences) {
     return http.post('/profile', preferences);
  },

  getPreferences(userId) {
    return http.get(`/profile/${userId}`);
 },
  

  setChoice(choice) {
      return http.post('/choice', choice);
  },

  getRandomMovieId(userId) {
    return http.get(`/users/${userId}`);
  },

  
  addToWatchlist(movie) {
    return http.post('/watchlist', movie)
  },

  getWatchlistMovies(userId) {
    return http.get(`/watchlist/${userId}`);
  },

}