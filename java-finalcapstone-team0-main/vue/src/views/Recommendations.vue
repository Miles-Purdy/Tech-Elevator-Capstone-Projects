<template>
  <div>
    <header-content />
      <body class="body">
        <div id="content">
            <h2 id="recommended-header">Based on your likes we recommend...</h2>
            <div class="movies-row">
              <div class="movie-card">
                <h3 class="card-title">{{this.suggestedArray[this.movieIndex].title}}</h3>
                <img id="movie-poster" v-bind:src="this.moviePosterPrefix + this.suggestedArray[this.movieIndex].poster_path" />
                <div class="movie-info">
                  <span class="overview">{{this.suggestedArray[this.movieIndex].overview}}</span>
                  <p class="release-date">Release Date: &nbsp; {{this.suggestedArray[this.movieIndex].release_date}}</p>
                  <p class="avg-rating">Average Rating: {{parseFloat(this.suggestedArray[this.movieIndex].vote_average).toFixed(2)}}/10</p>
                  <button class="watchlist-button" id="add-btn1" type="button" v-on:click="addMovieToWatchlist()">
                  Add To Watchlist
                  </button>
                </div>
              </div>
              <div class="movie-card">
                <h3 class="card-title">{{this.suggestedArray[this.movieIndex + 4].title}}</h3>
                <img id="movie-poster" v-bind:src="this.moviePosterPrefix + this.suggestedArray[this.movieIndex + 4].poster_path" />
                <div class="movie-info">
                  <span class="overview">{{this.suggestedArray[this.movieIndex + 4].overview}}</span>
                  <p class="release-date">Release Date: &nbsp; {{this.suggestedArray[this.movieIndex + 4].release_date}}</p>
                  <p class="avg-rating">Average Rating: {{parseFloat(this.suggestedArray[this.movieIndex + 4].vote_average).toFixed(2)}}/10</p>
                  <button class="watchlist-button" id="add-btn2" type="button" v-on:click="addMovieToWatchlistFour()">
                  Add To Watchlist
                  </button>
                </div>
              </div>
              <div class="movie-card">
                <h3 class="card-title">{{this.suggestedArray[this.movieIndex + 13].title}}</h3>
                <img id="movie-poster" v-bind:src="this.moviePosterPrefix + this.suggestedArray[this.movieIndex + 13].poster_path" />
                <div class="movie-info">
                  <span class="overview">{{this.suggestedArray[this.movieIndex + 13].overview}}</span>
                  <p class="release-date">Release Date: &nbsp; {{this.suggestedArray[this.movieIndex + 13].release_date}}</p>
                  <p class="avg-rating">Average Rating: {{parseFloat(this.suggestedArray[this.movieIndex + 13].vote_average).toFixed(2)}}/10</p>
                  <button class="watchlist-button" id="add-btn3" type="button" v-on:click="addMovieToWatchlistThirteen()">
                  Add To Watchlist
                  </button>
                </div>
              </div>
              <div class="more-suggestions">
                <button type="button" class="arrow" @click="reloadPage">&#129154;</button>
                <p class="next-text">More Movies</p>
              </div>
            </div> 
        </div>
      </body>    
    <footer-bar />
  </div>
</template>

<script>
//import movieCard
import databaseService from '../services/DatabaseService.js'
import FooterBar from '../components/FooterBar.vue'
import HeaderContent from '../components/HeaderContent.vue'
import movieService from '../services/MovieService.js'


export default {
  components: { HeaderContent, FooterBar },
    name: "recommendations",
      data() {
        return {
          suggestedArray: [],
          watchlistArray: [],
          displayNext: true,
          movie: {
            movieTitle: "",
            movieGenre: "", 
            moviePoster: "",
            movieId: "",
          },
          watchlistItem: {
            userId: "",
            movieId: "",
            movieTitle: "",
            posterPath: ""
          },
          moviePosterPrefix: "https://image.tmdb.org/t/p/original",
          apiKey: "b8575ac03a9ab9bf995e0b440f034434",
          movieIndex: 0,
          pageNumber: "",
        }
    },
    methods: {
      getMovieId(userId) {
        return databaseService.getRandomMovieId(userId);
      },


      makeSuggestedMoviesArray() {
        this.getMovieId(this.$store.state.user.id).then((movieId) =>{
        movieService.getSuggestion(movieId.data, this.apiKey).then(
          (response) => {
            response.data.results.forEach( movie => {
              this.suggestedArray.push(movie);

              console.log(movie)
              })
            });
            
          // this.setRandomMovie();
          }
        );
      },

   

      suggestionResults() {
        this.movie.movieTitle = this.suggestedArray[this.movieIndex].title;
         this.movie.moviePoster = this.moviePosterPrefix + this.suggestedArray[this.movieIndex].poster_path;
          document.getElementById("movie-poster").src = this.movie.moviePoster;
      },

      reloadPage() {
      window.location.reload();
    },

    addMovieToWatchlist() {
      this.watchlistItem.userId = this.$store.state.user.id;
      this.watchlistItem.movieId = this.suggestedArray[this.movieIndex].id;
      this.watchlistItem.movieTitle = this.suggestedArray[this.movieIndex].title;
      this.watchlistItem.posterPath = this.suggestedArray[this.movieIndex].poster_path;
      databaseService.addToWatchlist(this.watchlistItem);
      let btn = document.getElementById("add-btn1")
      btn.innerHTML = "<span style='color: chartreuse'>&#x2714; Added!</span>";
    },

    addMovieToWatchlistFour() {
      this.watchlistItem.userId = this.$store.state.user.id;
      this.watchlistItem.movieId = this.suggestedArray[this.movieIndex + 4].id;
      this.watchlistItem.movieTitle = this.suggestedArray[this.movieIndex + 4].title;
      this.watchlistItem.posterPath = this.suggestedArray[this.movieIndex + 4].poster_path;
      databaseService.addToWatchlist(this.watchlistItem);
      let btn = document.getElementById("add-btn2")
      btn.innerHTML = "<span style='color: chartreuse'>&#x2714; Added!</span>";
    },

    addMovieToWatchlistThirteen() {
      this.watchlistItem.userId = this.$store.state.user.id;
      this.watchlistItem.movieId = this.suggestedArray[this.movieIndex + 13].id;
      this.watchlistItem.movieTitle = this.suggestedArray[this.movieIndex + 13].title;
      this.watchlistItem.posterPath = this.suggestedArray[this.movieIndex + 13].poster_path;
      databaseService.addToWatchlist(this.watchlistItem);
      let btn = document.getElementById("add-btn3")
      btn.innerHTML = "<span style='color: chartreuse'>&#x2714; Added!</span>";
    },

    },
    created() {
      this.makeSuggestedMoviesArray();
    }
}


</script>

<style>
/* uses styles from the Favorites.vue page*/

#recommended-header {
  font-size: 29px;
  color: #eeaa33;
  margin-bottom: 40px;
}

#content {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;
  background: transparent;
  margin: 30px;
}


.movies-row {
  display: flex;
  background: transparent;
  align-items: flex-start;
  margin-left: 6.5em;
}


.arrow {
  color: white;
  font-size: 65px;
  background: transparent;
  border: none;
  margin: 10px;
  cursor: pointer;
}

.more-suggestions {
  background: transparent;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20%;
}


.next-text {
  font-size: 16px;
  padding: 0px;
  margin-top: -8px;
}

.movie-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  background: transparent;
}

.card-title {
  margin: 0px;
  font-size: 25px;
  background: transparent;
  display: flex;
  flex-grow: 2;
  width: 390px;
  justify-content: space-around;
  height: 70px;
  text-align: center;
}

.release-date, .avg-rating {
  font-size: 14px;
  margin-top: 10px;
}

.overview {
  width: 344px;
  font-size: 13px;
  height: fit-content;
  background: transparent;
  margin-bottom: 10px;
}

.movie-info {
  display: flex;
  flex-direction: column;
  flex-basis: content;
  width: 344px;
  background: transparent;
  margin: 10px;
}

.watchlist-button {
  font-size: 15px;
  color: white;
  border: 2px solid #eeaa33;
  border-radius: 5px;
  text-decoration: none;
  padding: 8px;
  cursor: pointer;
  width: 250px;
  margin-left: 65px;
}
</style>