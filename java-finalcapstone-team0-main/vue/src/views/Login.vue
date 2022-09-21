<template>
  <div class="log-in">
      <header-content  />
    <body class="body">
    <div id="login" class="text-center">
      <form class="form-signin" @submit.prevent="login">
        <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
        <div
          class="alert alert-danger"
          role="alert"
          v-if="invalidCredentials"
        >Invalid username and password!</div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >Thank you for registering, please sign in.</div>
        <div class="login-box">
          <label for="username" class="sr-only">Username</label>
          <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
          />
          <br>
          <label for="password" class="sr-only">Password</label>
          <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
          />
          <br>
          </div>
        <div class="account-links">
          <router-link :to="{ name: 'register' }" class="nav-link">Need an account?</router-link>
          <br>
          <button type="submit" class="btn">Sign in</button>
        </div>
      </form>
    </div>
    </body>
    <footer-bar />
  </div>
</template>

<script>
import authService from "@/services/AuthService";
import HeaderContent from '@/components/HeaderContent.vue';
import FooterBar from '@/components/FooterBar.vue';


export default {
  name: "login",
  components: { HeaderContent,
    FooterBar
    },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/movies");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
.log-in {
  display: grid;
  
  grid-template-rows: .25fr 3fr .25fr;
  grid-template-areas: 
  ".header", 
  ".home", 
  ".footer";
  
}

#username, #password {
  background: white;
}

.account-links button {
  width: 90px;
  font-size: 16px;
}

.form-signin {
  border: 2px solid #EEAA33;
  border-radius: 5px;
  padding: 40px;
  margin: 10px;
}

.nav-link{
 color: #f8ba4fc5;
}

.nav-link:hover{
 color: red;
}

.text-center {
  background: transparent;
  padding: 100px;
}

</style>
