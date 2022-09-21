import axios from 'axios';

// Inserted local host connection
const http = axios.create({
  baseURL: "http://localhost:9000"});

export default {

  login(user) {
    return http.post('/login', user)
  },

  register(user) {
    return http.post('/register', user)
  },

  giveAdminStatus(user) {
    return http.post('/admin', user)
  }

}
