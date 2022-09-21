<template>
  <div class="homepage">
    <header-content/>
    <body class="body">
     

          <h1>Swipe Right</h1>
          <h2 style="text-align: center;">2 of 3 people watch the wrong movie.<br>
            <span style="color: red;">Stop</span> browsing, we do that for you.<br>
            We match you with your perfect MovieMate tonight.
          </h2>
          <h3>Welcome to <span style="color: #EEAA33;">MovieMates!</span></h3>
          <p class="hero-text">
            We're here to provide custom movie recommendations tailored to your specific preferences. Love comedies? Great, we'll make you laugh. Interested in history? We've got you covered, history 
            buffs. You'll learn something tonight.
          </p>
          <p class="hero-text" style="text-align: center;">The process is simple.</p>
          <p class="hero-text">Register here and let us know your likes and dislikes. MovieMates will then use that information to curate a list of available movies that you can choose to watch.
          </p>

          <ul class="list">Here's what you get as a <span style="color: #EEAA33;">MovieMates</span> member:
            <li>Search, browse, and add movies to your own personal wishlist.</li>
            <li>Save time looking for what to watch next.</li>
            <li>Enjoy the movie with your MovieMate!</li>
            <li>Be the popular one amongst your friends!</li>
            <li>Be known as the one who recites movie quotes all day.</li>
            <li>Boost likeability score by 76.2%!</li>
          </ul>

          <router-link class="register-button" v-bind:to="{ name: 'register' }">Click Here to Register</router-link>

   

    </body>
    <footer-bar />
  </div>
</template>

<script>
import HeaderContent from "@/components/HeaderContent.vue";
import FooterBar from "@/components/FooterBar.vue";

export default {
  name: "home",
  components: { HeaderContent, FooterBar },
};
</script>

<style>
.homepage {
  display: grid;
  grid-template-rows: .25fr 3fr .25fr;
  grid-template-areas: 
  ".header", 
  ".home", 
  ".footer";
}

.body {
   margin: 0;
   background-image: 
   linear-gradient(
              rgba(0,0,51, 0.6),
              #000033 ),

              
   
   url('../assets/movie_poster_collage.png');
   
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.content {
  background-color : transparent;
}

h2 {
  background: transparent;
}

.register-button {
  font-size: 25px;
  color: white;
  border: 2px solid #eeaa33;
  border-radius: 5px;
  text-decoration: none;
  padding: 8px;
}

.body > h1 {
  color: #EEAA33;
  background: transparent;
}
.body > h3 {
  font-weight: normal;
  background: transparent;
}

.hero-text {
  font-size: 20px;
  box-sizing: border-box;
  /* text-indent: 45px; */
  width: 65%;
  height: 65%;
  background: transparent;
}

.list {
  font-size: 25px;
  background: transparent;
}

.list > li {
  font-size: 15px;
  background: transparent;
}
</style>