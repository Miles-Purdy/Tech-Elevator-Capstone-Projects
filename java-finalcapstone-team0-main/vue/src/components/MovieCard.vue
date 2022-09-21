<template>
  <div>
    <h3>Movie Card Placeholder</h3>
    <img id="movie-poster" v-bind:src="movie.moviePoster" />
    <div class="watchlist">
    <!-- v-on:click.prevent="addToWatchlist(movie)" -->
      <button type="button" class="mc-watchlist-btn">Add to Watchlist</button>
    </div>
  </div>
</template>

<script>
// import DatabaseService from '@/services/DatabaseService.js';
// import MovieService from '@/services/MovieService.js';
// import Movies1 from '@/components/Movies1.vue';

export default {
  name: "movie-card",
  props: {
    // Movies1
  },
  data() {
    return {
      movie: {
        movieId: "",
        movieTitle: "",
        moviePoster: "",
        watched: false,
      },
    };
  },
  methods: {
    // setWatched(value) {
    // },
    // addToWatchlist(movie) {
    //     // let addedMovie = Object.assign({ watched: false }, movie);
    // }
  },
};
</script>

// //   getRandomMovieId(userId) {
//     return http.get('/recommendations', userId);
//   }

<style>
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 250px;
  height: 550px;
  margin: 20px;
}
.watchlist {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.mc-watchlist-btn {
  border: 1px solid #eeeeee;
  color: #eeeeee;
  justify-content: right;
  width: 45%;
}
</style>