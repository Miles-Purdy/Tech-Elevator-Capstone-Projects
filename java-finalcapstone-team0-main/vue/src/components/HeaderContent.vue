<template>
  <div class="header" id="nav">
      <router-link v-bind:to="{ name: 'home' }">
        <img class="header-img" src="../assets/logo.png" />
        </router-link>
        <router-link v-bind:to="{ name: 'home' }">
        <p id="name">MovieMates</p>
        </router-link>
     
      <ul class="user-buttons">
     
    
        <router-link to="/login" class="header btn" id="sign-in" tag="button" 
        v-if="$store.state.token === ''">Sign in</router-link>
        <li>
          <router-link
            v-bind:to="{ name: 'movies' }" class="movies-button"
            v-if="$store.state.token != ''" v-show="this.$route.name != 'movies'">Start Swiping</router-link>
        </li>

         <li>
          <router-link to="/watchlist" class="watchlist-btn"
          v-if="$store.state.token !=''">My Watchlist</router-link>
        </li>
        
        <li>
            <router-link to="/recommendations" class="rec-btn"
          v-if="$store.state.token !=''">Recommendations</router-link>
          </li>
        
        <li>
          <router-link
            v-bind:to="{ name: 'profile' }" class="profile-button"
            v-if="$store.state.token != ''">Profile </router-link>
        </li>
       <!-- <li>
          <router-link
            v-bind:to="{ name: 'admin' }" class="admin-button"
            v-show="this.user.role == 'admin' && this.$route.name != 'admin'">Admin</router-link>
        </li> -->
        <li>
          <router-link
            v-bind:to="{ name: 'logout' }" class="logout-button"
            v-if="$store.state.token != ''">Logout</router-link>
        </li>
      </ul>
    </div>
</template>
<script>
export default {
    name: "header-content"
}
</script>
<style>
#sign-in {
  margin-right: -35px;
  margin-top: 15px;
  cursor: pointer;
  border: none;
  background: transparent;
}
.header {
  display: flex;
  font-weight: 400;
  color: white;
  text-align: center;
  border: none;
  vertical-align: middle;
  margin-right: 10px;
  margin-bottom: 10px;
  font-size: 21px;
  height: 95px;
}
.user-buttons {
  display: flex;
  flex-grow: 2;
  justify-content: flex-end;
  list-style: none;
  background: transparent;
}
li {
  margin: 10px;
 
}
.profile-button, .logout-button, .movies-button, .watchlist-btn, .rec-btn{
  color: white;
  text-decoration: none;
}
#name {
  margin: 32px 0px;
  color: white;
  text-decoration: none;
  font-size: 38px;
  padding-bottom: 10px;
  
}

a {
  text-decoration: none;
  
}
</style>