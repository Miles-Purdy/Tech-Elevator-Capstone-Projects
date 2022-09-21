<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Sora");

/* #app {
  background-image: url('assets/bg.jpg');
  background-repeat: no-repeat;
} */

.body {
  color: #EEEEEE;
  display: flex;
  flex-direction: column;
  align-items: center;
  
}

/* .header {
  display: flex;
  color: #EEAA33;
  justify-content: space-between;
} */

h1 {
  font-size: 70px;
  color: #EEAA33;
}

p {
  font-size: 35px;
}

* {
  background: #000033; 
  font-family: "Sora";
}

.login-box {
  display: flex;
  flex-direction: column;

}

.account-links {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.btn {
  border: 1px solid #EEEEEE;
  width: 20%;
  color: #EEEEEE;
 justify-content: right;
}

  
.header-img {
  height: 65px;
  padding: 8px;
}

/* #ageCheckbox {
  vertical-align: baseline; 
  
  
} */



</style>

