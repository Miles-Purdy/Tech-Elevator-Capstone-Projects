<template>
  <div>
    <header-content />
      <body class="body">
          <h1 class="watchlist-header">My Watchlist</h1>
        <div class="watchlist-container">
        <div class="watch-list" v-for="object in watchlistArray" v-bind:key="object.movieId">
          <h3 class="watchlist-title">{{object.movieTitle}}</h3>
        <img class="watchlist-poster" v-bind:src="moviePosterPrefix + object.posterPath" />
        </div>
        </div>
      </body>    
    <footer-bar />
  </div>
</template>

<script>
import FooterBar from '../components/FooterBar.vue'
import HeaderContent from '../components/HeaderContent.vue'
import databaseService from '../services/DatabaseService.js'


export default {
  components: { HeaderContent, FooterBar },
    name: "watchlist",

    data() {
      return {
        movie: {
        movieTitle: "",
         movieGenre: "", 
         moviePoster: "",
         movieId: "",
        },

        watchlistItem: {
          userId: "6",
          movieId: "510",
          movieTitle: "Title",
          posterPath: "adsfguilasdsa"
        },
        watchlistArray: [],
        moviePosterPrefix: "https://image.tmdb.org/t/p/original",
        apiKey: "b8575ac03a9ab9bf995e0b440f034434"
      }
    },

    methods: {
      makeWatchlistMoviesArray() {
        databaseService.getWatchlistMovies(this.$store.state.user.id).then(
          (response) => {
            response.data.forEach( movieId => {
              this.watchlistArray.push(movieId);

              console.log(movieId)
              })
            });
      }

    },

    created() {
        this.makeWatchlistMoviesArray();
      }
}



</script>

<style>
/* uses styles from the Favorites.vue page*/

.watchlist-header {
  margin: 20px;
}

.watchlist-title {
  background: transparent;
  width: 280px;
  height: 60px;
  margin: 0px;
  text-align: center;
  bottom: 0px;
  padding-top: 15px;
}

.watch-list {
  display: flex;
  flex-direction: column;
  background: transparent;
  align-items: center;
}

.watchlist-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  background: transparent;
  margin: 25px;
  padding: 5px;
}


.watchlist-poster {
  width: 280px;
  height: 410px;
  border: 2px outset #eeaa33;
  margin: 18px;
  margin-top: 0px;
}
</style>