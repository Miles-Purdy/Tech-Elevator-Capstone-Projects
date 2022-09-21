<template>
  <div>
    <header-content />
    <body class="body">
      <div class="swiper">
        <div v-show="!displayNext" class="thumbs-down">
          <button
            v-on:click.prevent="setRandomMovie(), createChoice()"
            class="thumbs-down fa fa-thumbs-down"
          />
          <p>I hated it!</p>
        </div>
        <div v-show="displayNext" id="instructions">
          <img id="box-img" src="../assets/logo.png" />
          <p id="name-box">MovieMates</p>
          <h4>Click Next to get started...</h4>
        </div>
        <img
          v-show="!displayNext"
          id="movie-poster"
          v-bind:src="movie.moviePoster"
        />
        <div v-show="!displayNext" class="thumbs-up">
          <button
            v-on:click.prevent="setRandomMovie(), createChoiceTrue()"
            class="thumbs-up fa fa-thumbs-up"
          />
          <p>I loved it!</p>
        </div>
      </div>
      <button
        v-if="displayNext"
        class="btn"
        v-on:click.prevent="setRandomMovie()"
      >
        Next..
      </button>
      <button v-else class="btn" v-on:click.prevent="setRandomMovie()">
        Skip
      </button>
      <div class="buttons"></div>
      <div class="buttons">
        <button v-show="!displayNext" type="button" class="suggest">
          <router-link to="/recommendations" class="rec-btn"
          v-if="$store.state.token !=''">I'm ready for a suggestion!</router-link>
        </button>
        <!-- <router-link to="/recommendations" class="rec-btn"
          v-if="$store.state.token !=''">I'm ready for a suggestion!</router-link> -->
      </div>
    </body>
    <footer-bar />
  </div>
</template>
<script>
import HeaderContent from "@/components/HeaderContent.vue";
import FooterBar from "@/components/FooterBar.vue";
import databaseService from "../services/DatabaseService.js";
import MovieService from "@/services/MovieService.js";
export default {
  name: "movies",
  components: {
    HeaderContent,
    FooterBar,
  },
  data() {
    return {
      choice: {
        userId: "",
        movieId: "",
        liked: false,
      },
      topMoviesArray: [],
      filteredTopMoviesArray: [],
      displayNext: true,
      movie: {
        movieTitle: "",
        movieGenre: "",
        moviePoster: "",
        movieId: "",
      },
      moviePosterPrefix: "https://image.tmdb.org/t/p/original",
      apiKey: "b8575ac03a9ab9bf995e0b440f034434",
      movieIndex: "",
      pageNumber: "",
    };
  },
  created() {
    this.makeTopMoviesArray();
  },
  methods: {
    createChoice() {
      this.choice.userId = this.$store.state.user.id;
      this.choice.movieId = this.topMoviesArray[this.movieIndex].id;
      this.choice.liked = false;
      databaseService.setChoice(this.choice);
    },
    createChoiceTrue() {
      this.choice.userId = this.$store.state.user.id;
      this.choice.movieId = this.topMoviesArray[this.movieIndex].id;
      this.choice.liked = true;
      databaseService.setChoice(this.choice);
    },
    makeRandomId(length) {
      return (this.movieIndex = Math.floor(Math.random() * length));
    },
    makeTopMoviesArray() {
      for (let i = 1; i <= 25; i++) {
        this.pageNumber = i;
        MovieService.getTopRatedMovies(this.apiKey, this.pageNumber).then((response) => {
            response.data.results.forEach((movie) => {
              this.topMoviesArray.push(movie);
            });
        });
      }
      this.saveTopMoviesArray(this.topMoviesArray);
      this.filterTopMoviesArrayByGenre();
    },
    saveTopMoviesArray(array) {
      this.$store.commit("SET_TOP_MOVIES_ARRAY", array);
    },
    // Compare movie $store to users preferences in DB
    filterTopMoviesArrayByGenre() {
      let movieStore = this.$store.state.topMoviesArray;
      // this.makeRandomId(movieStore.length);
      databaseService.getPreferences(this.$store.state.user.id)
        .then((response) => {
          if (response.status === 200) {
            console.log("HTTP Request OK");
            // Loops through user preferences in DB
            response.data.forEach((genreObj) => {
              console.log("AAAA");
            // Loops through $stored array of unfiltered movies
              movieStore.forEach((movie) => {
                console.log("BBBB");
                // compared genre_ids in $store to genreIds from object
                if (movie.genre_ids.includes(genreObj.genreId)) {
                    console.log("CCCC");
                    this.filteredTopMoviesArray.push(movie);
                  }
                });
              });
              console.log("DDDD");
          }
        console.log("EEEE");
        this.saveTopMoviesArrayByGenre(this.filteredTopMoviesArray);
        console.log("FFFF");
        });
    },
    saveTopMoviesArrayByGenre(array) {
      this.$store.commit("SET_FILTERED_TOP_MOVIES_ARRAY", array);
    },
    setRandomMovie() {
      if(this.topMoviesArray) {
        let filtered = this.$store.state.filteredTopMoviesArray;
        let index = this.makeRandomId(filtered.length);
        // this.filterTopMoviesArrayByGenre(); don't need to call
        console.log("clicked1")
        this.displayNext = false;
        console.log("clicked2")
        this.movie.movieTitle = filtered[index].title;
        console.log("clicked3");
        this.movie.moviePoster = this.moviePosterPrefix + filtered[index].poster_path;
        this.movie.movieGenre = filtered[index].genre_ids[0];
      }
    },
  }
}
</script>
<style>
.swiper {
  display: flex;
  align-items: center;
  height: 100%;
  background: transparent;
  margin: 30px;
}
.thumbs-down,
.thumbs-up {
  display: flex;
  flex-direction: column;
  padding: 15px;
  font-size: 48px;
  align-items: center;
  background: transparent;
  border: none;
  cursor: pointer;
}
p,
i {
  background: transparent;
}
.thumbs-down {
  color: crimson;
}
.thumbs-up {
  color: chartreuse;
}
.buttons {
  display: flex;
  margin-left: 10px;
}
.suggest,
.watch {
  color: aliceblue;
  height: 35px;
  margin: 30px;
  font-size: 22px;
  border-color: #EEAA33;
  border-radius: 5px;
}
.not-watched {
  color: aliceblue;
  height: 35px;
  margin: 30px;
  font-size: 22px;
  border-color: #EEAA33;
  border-radius: 5px;
}
figcaption {
  margin-right: 35px;
}
#movie-poster {
  width: 340px;
  height: 500px;
  border: 2px outset #EEAA33;
}
#instructions {
  width: 340px;
  height: 500px;
  border: 2px outset #EEAA33;
}
#instructions > h4 {
  font-size: 40px;
  padding: 10px;
  margin-top: 150px;
  text-align: center;
}
#box-img {
  width: 50px;
  height: 40px;
  margin-top: 20px;
  margin-left: 15px;
}
#name-box {
  float: right;
  text-align: right;
  justify-content: top;
  margin-top: 20px;
  margin-right: 50px;
}
</style>