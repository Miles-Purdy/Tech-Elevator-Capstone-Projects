<template>
  <div>
      <header-content />
      <h1> Admin Page </h1>
      <footer-bar />
  </div>
</template>

<script>
import FooterBar from '../components/FooterBar.vue'
import HeaderContent from '../components/HeaderContent.vue'
export default {
  components: { HeaderContent, FooterBar },

    name: "admin"
}
</script>

<style>

</style>