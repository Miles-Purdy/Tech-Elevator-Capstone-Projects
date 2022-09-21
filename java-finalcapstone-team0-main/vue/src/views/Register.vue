<template>
 <div class="register">
    <header-content />
    <body class="body">
      <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
          <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="registrationErrors"
          >
            {{ registrationErrorMsg }}
          </div>
          <div class="login-box">
            <label for="email" class="sr-only">Email Address</label>
            <input
            type="text"
            id="email"
            class="form-control"
            placeholder="Email Address"
            v-model="user.emailAddress"
            required
            autofocus
           />
           <br>
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
            <input
            type="password"
            id="confirmPassword"
            class="form-control"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
            />
            <br>
            <label class="form-control">
  <input type="checkbox" name="checkbox"  id="ageCheckbox"
            class="form-control"
            v-model="user.checkbox"
            required />
  I am at least 16 years old
</label><br>
            
            <div class="account-links">
              <router-link :to="{ name: 'login' }" class="nav-link">Have an account? Log in here.</router-link>
              <br>
            
              <button class="btn btn-lg btn-primary btn-block" type="submit">
              Create Account
              </button>
            </div>
            </div>
        </form>
      </div>
    </body>
    <footer-bar />
  </div>
</template>

<script>

import FooterBar from "../components/FooterBar.vue";
import authService from "../services/AuthService";
import HeaderContent from '../components/HeaderContent.vue';

export default {
  components: { HeaderContent, FooterBar },
  name: "register",
  data() {
    return {
      user: {
        emailAddress: "",
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
.register {
  display: grid;
  grid-template-rows: .25fr 3fr .25fr;
  grid-template-areas:
   ".header", 
  ".home", 
  ".footer";
}

#email, #confirmPassword {
  background: white;
}

.form-register {
   border: 2px solid #EEAA33;
  border-radius: 5px;
  padding: 40px;
  margin: 10px;
}

.form-control {
  height: 26px;
  display: flex;
  align-items: center;
}

</style>
