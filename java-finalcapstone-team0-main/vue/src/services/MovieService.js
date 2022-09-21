import axios from 'axios';

// Inserted local host connection
const http = axios.create({
  baseURL: "https://api.themoviedb.org/3"});

export default {
// data() {
//     return {
//         apiKey: b8575ac03a9ab9bf995e0b440f034434;
//     };
// },
// Generic CRUD methods to obtain information from movie API to display movie name, poster, description



getTopRatedMovies (apiKey, pageNumber) {
  return http.get(`/movie/top_rated?api_key=${apiKey}&language=en-US&page=${pageNumber}&language=en-US`);
},

getMovie(movieId, apiKey) {
  return http.get(`/movie/${movieId}?api_key=${apiKey}&language=en-US&append_to_response=release_dates`);
},

getSuggestion(movieId, apiKey) {
return http.get(`/movie/${movieId}/recommendations?api_key=${apiKey}`);
}
}
