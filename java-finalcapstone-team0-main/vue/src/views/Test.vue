<template>
  <!-- Test HTTP Methods Page -->
  <div>
   
    <img id="movie-poster" />
  </div>
</template>

<script>
import MovieService from "@/services/MovieService.js";
// const apiKey = "b8575ac03a9ab9bf995e0b440f034434";
export default {
  data() {
    return {
      topMoviesArray: [],
      movie: {
         movieTitle: "",
         movieGenre: "", 
         moviePoster: "",
      },
      moviePosterPrefix: "https://image.tmdb.org/t/p/original",
      apiKey: "b8575ac03a9ab9bf995e0b440f034434",
      movieIndex: "",
      pageNumber: "",
    };
  },
  methods: {
    makeRandomId() {
      return this.movieIndex = Math.floor(Math.random() * 499);
    },

    makeTopMoviesArray() {
      // need to loop through X num of pages and store the movie ids
      // The movie IDs need to be stored in an array
      // The array of movies needs to update the obtainMovie method

      // this loop results in 500 movies
      for (let i = 1; i <= 25; i++) {
        this.pageNumber = i;
        MovieService.getTopRatedMovies(this.apiKey, this.pageNumber).then(
          (response) => {
            response.data.results.forEach( movie => {
              console.log(movie)
              this.topMoviesArray.push(movie);
            });
          this.setRandomMovie();
          }
        );
      }
              // var movieResult = this.topMoviesArray.find(movie => movie.id === 1);
    },

    // obtainMovie() {
    //   this.makeRandomId();
    //   MovieService.getMovie(this.movieId, this.apiKey).then((response) => {
    //     this.movieTitle = response.data.original_title;

    //     // Get first genre listed in movie details from API
    //     this.movieGenre = response.data.genres.findIndex((genreArray) => {
    //       return genreArray[0];
    //     }).name;
    //     this.moviePoster = this.moviePosterPath + response.data.poster_path;
    //     document.getElementById("movie-poster").src = this.moviePoster;
    //   });
    // },

    setRandomMovie() {
      if (this.topMoviesArray){
      this.makeRandomId();
      
      this.movie.movieTitle = `${this.topMoviesArray[this.movieIndex].original_title}`;
      document.getElementById("movie-poster").src = this.movie.moviePoster;
      this.movie.moviePoster = this.moviePosterPrefix + this.topMoviesArray[this.movieIndex].poster_path;
    }
    }
  },
  created() {
    // this.obtainMovie();
    this.makeTopMoviesArray();
    // this.showRandomMovie();
  },
};

</script>

<style>
</style>
