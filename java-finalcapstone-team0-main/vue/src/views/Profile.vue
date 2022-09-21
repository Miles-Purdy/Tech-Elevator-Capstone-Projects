<template>
  <div>
    <header-content />
    <body class="body">
        <h1>User Settings</h1>
        <h3>Select your preferences below</h3>
        <form class="settings-form" @submit.prevent="settingSave">
            <h4>Preferred MPA Rating:</h4>
         
            <label for="movie-ratings"></label>
            <div class="rating">
            <input  v-model="mpaaRating" type="checkbox" id="rated-R" name="rated-R" value="R">
            <label for="rated-r">Rated R</label><br>
          </div>
          <div class="rating">
            <input  v-model="mpaaRating" type="checkbox" id="rated-pg13" name="rated-pg-13" value="PG-13">
            <label for="rated-pg-13">Rated PG-13</label><br>
          </div>
           <div class="rating">
            <input  v-model="mpaaRating" type="checkbox" id="rated-pg" name="rated-pg" value="PG">
            <label for="rated-pg">Rated PG</label><br>
          </div>
           <div class="rating">
            <input  v-model="mpaaRating" type="checkbox" id="rated-g" name="rated-g" value="G">
            <label for="rated-g">Rated G</label><br>
          </div>
          
             <hr class="divider">
          <h4>Choose your preferred genres:</h4>
          <div class="genre">
            <input  v-model="preferences.genres" type="checkbox" id="drama" name="drama" value="18">
            <label for="drama">Drama</label><br>
          </div>
          <div class="genre">
            <input v-model="preferences.genres"  type="checkbox" id="comedy" name="comedy" value="35">
            <label for="comedy">Comedy</label><br>
          </div>
          <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="action" name="action" value="28">
            <label for="action">Action</label><br>
          </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="romance" name="romance" value="10749">
            <label for="romance">Romance</label><br>
          </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="sci-fi" name="sci-fi" value="878">
            <label for="sci-fi">Sci-fi</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="thriller" name="thriller" value="53">
            <label for="thriller">Thriller</label><br>
          </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="family" name="family" value="10751">
            <label for="family">Family</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="adventure" name="adventure" value="12">
            <label for="adventure">Adventure</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="animation" name="animation" value="16">
            <label for="animation">Animation</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="crime" name="crime" value="80">
            <label for="crime">Crime</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="fantasy" name="fantasy" value="14">
            <label for="fantasy">Fantasy</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="history" name="history" value="36">
            <label for="history">History</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="horror" name="horror" value="27">
            <label for="horror">Horror</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="music" name="music" value="10402">
            <label for="music">Music</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="mystery" name="mystery" value="9648">
            <label for="mystery">Mystery</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="tv-movie" name="tv-movie" value="10770">
            <label for="tv-movie">TV Movie</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="war" name="war" value="10752">
            <label for="war">War</label><br>
            </div>
            <div class="genre">
            <input v-model="preferences.genres" type="checkbox" id="western" name="western" value="37">
            <label for="western">Western</label><br>
           </div>
           <div class ="genre">
           </div>
           <div class="genre">
             </div>
             <div class ="genre">
           </div>
           <div class="genre">
             </div>
           <hr class="divider">


            <button v-on:click="setPreferences" class="settings-save" id="stg-sve" type="submit" @click="reloadPage">
              Save Settings
            </button>

        </form>

    </body>

    <footer-bar />
  </div>
</template>

<script>
import databaseService from "@/services/DatabaseService.js";
import FooterBar from '../components/FooterBar.vue'
import HeaderContent from '../components/HeaderContent.vue'

export default {
  components: { HeaderContent, FooterBar },

    name: "profile",

    data() {
      return {
        preference: {
          title: ''
        },

        preferences: {
          genres: [],
          userId: this.$store.state.user.id
        },
        mpaaRating: [],
         checkedGenres: [],
      }
    },

    methods: {

      settingSave() {
        databaseService
          .setPreferences(this.preferences)
          console.log("settings function")
          .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_PREFERENCES", response.data.preferences);
          }
        })
      },

      reloadPage() {
      window.location.reload();
      console.log("hello");
    },

      addToList() {
        this.$store.preferences.unshift(this.preference);
      }
    }

}
</script>

<style>

h1 {
    color: white;
    font-size: 38px;
}


.settings-form {
  border: 2px solid #EEAA33;
    padding: 20px;
    border-radius: 8px;
    width: 50%;    
    text-align: center; 
  
}

.genre {
  width: 22%;
  margin-left: 3%;
  margin-right:0%;
  float: left;
  margin-bottom: 40px;
  text-align: left;
}

.rating {
  width: 22%;
  margin-left: 3%;
  margin-right:0%;
  float: left;
  margin-bottom: 40px;
  text-align: left;
}

hr.divider {
  margin-top: 10%;
  margin-bottom: 7%;
  border-top: .5px solid white;
  border-radius: 1px;
}
 
 #movie-ratings, option {
      background-color: white;
      margin-bottom: 10px;
      

  }


.settings-save {
 
    width: 25%;
    color: white;
    border: 1px solid white;
    border-radius: 3px;
    padding: 9px;
    margin: 7px;  
    cursor: pointer;
}


</style>