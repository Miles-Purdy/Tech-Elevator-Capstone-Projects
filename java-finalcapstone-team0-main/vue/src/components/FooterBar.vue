<template>
  <div class="footer">
    Copyright &copy; TEam Zero
    <div class="privilege">
      <router-link
            v-bind:to="{ name: 'privileges' }" class="privileges-button"
            v-if="$store.state.token != '' && this.$route.name != 'privileges'">Request Admin Privileges</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "footer-bar",
};
</script>

<style>

.footer {
  color: #AAAAAA;
  display: flex;

  justify-content: center;
  align-items: center;



  bottom: 0;
  width: 100%;
  height: 2.5rem;  

}

.privileges-button {
  padding: 15px;
  justify-content: flex-end;
  text-decoration: none;
  color:khaki;
  font-size: 10px;
}
</style>