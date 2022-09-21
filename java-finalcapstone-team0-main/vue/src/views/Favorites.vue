<template>
  <div>
      <header-content />
    <body class="body">
      <h1 class="title">My Favorites</h1>
       <favorite-list/>
    </body>
    <footer-bar/>
    </div>
  
</template>

<script>
import FavoriteList from '../components/FavoriteList.vue'
import FooterBar from '../components/FooterBar.vue'
import HeaderContent from '../components/HeaderContent.vue'
export default {
  components: { HeaderContent, FooterBar, FavoriteList },
    name: "favorites"
}

</script>

<style>

.title {
    font-size: 60px;
    color: #EEAA33;
    align-content: center;
    margin-top: 7%;
}

</style>
