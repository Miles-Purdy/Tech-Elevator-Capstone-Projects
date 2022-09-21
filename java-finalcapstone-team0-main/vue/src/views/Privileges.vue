<template>
  <div>
      <header-content />
      <body class="body">
          <h2>Please fill out this form to request Admin privileges</h2>
          <form class="form-admin" @submit.prevent="request">
              <h3 class="h3 mb-3 font-weight-normal">Account Details</h3>
            <div class="admin-box">
            <label for="firstName" class="sr-only">First Name</label>
            <input
            type="text"
            id="firstName"
            class="admin-control"
            placeholder="First Name"
            v-model="admin.firstName"
            required
            autofocus
           />
           <br>
           <label for="lastName" class="sr-only">Last Name</label>
            <input
            type="text"
            id="lastName"
            class="admin-control"
            placeholder="Last Name"
            v-model="admin.lastName"
            required
            autofocus
           />
           <br>
            <label for="email" class="sr-only">Account Email Address</label>
            <input
            type="text"
            id="email"
            class="admin-control"
            placeholder="Account Email Address"
            v-model="admin.emailAddress"
            required
            autofocus
           />
           <br>
            <label for="username" class="sr-only">Account Username</label>
            <input
            type="text"
            id="username"
            class="admin-control"
            placeholder="Account Username"
            v-model="admin.username"
            required
            autofocus
           />
            <br>
            <label for="password" class="sr-only">Account Password</label>
            <input
            type="password"
            id="password"
            class="admin-control"
            placeholder="Account Password"
            v-model="admin.password"
            required
            />
            <h4>Reason for Request</h4>
            <select name="movie-ratings" id="admin-options" class="admin-control">
                <option value="add-movies">I want to be able to add movies to the resource</option>
                <option value="delete-movies">I want to be able to delete movies from the resource</option>
                <option value="moderate">I want to moderate other admins</option>
                <option value="search-title">I want to search movies by title</option>
            </select><br>

            <button class="btn btn-lg btn-primary btn-block" type="submit" id="admin-submit">
              Request Admin Privileges
              </button>
            </div>
          </form>
      </body>
      <footer-bar />
  </div>
</template>

<script>
import FooterBar from '../components/FooterBar.vue'
import HeaderContent from '../components/HeaderContent.vue'
export default {
  components: { HeaderContent, FooterBar },

    name: 'privileges',

    data() {
        return {
        admin: {
            firstName: "",
            lastName: "",
            emailAddress: "",
            username: "",
            password: "",
        }
      }
    }
}
</script>

<style>

.admin-control {
    background-color: white;
    height: 25px;
    width: 235px;
    padding: 13px;
    margin: 9px;
}

.form-admin {
    padding: 35px;
    border: 2px solid #eeaa33;
    border-radius: 4px;
    margin: 7px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

#admin-submit {
    width: 180px;
}

.admin-box {
    display: flex;
    flex-direction: column;
    align-items: center;
}


</style>