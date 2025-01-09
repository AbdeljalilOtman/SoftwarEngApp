<template>
    <div class="login-page container-small">
      <h2 class="mb-2">Login</h2>
      <form @submit.prevent="login">
        <div>
          <label>Email:</label>
          <input v-model="email" type="email" required />
        </div>
        <div>
          <label>Password:</label>
          <input v-model="password" type="password" required />
        </div>
        <button type="submit">Login</button>
      </form>
      <p class="mt-2">
        Don't have an account? <router-link to="/register">Register here</router-link>
      </p>
    </div>
  </template>
  
  <script>
  import { mapActions } from 'vuex';
  
  export default {
    name: 'Login',
    data() {
      return {
        email: '',
        password: ''
      };
    },
    methods: {
      ...mapActions(['loginUser']),
      async login() {
        try {
          await this.loginUser({
            email: this.email,
            password: this.password
          });
          this.$router.push('/dashboard');
        } catch (error) {
          alert('Login failed. Please check console for details.');
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .login-page h2 {
    margin-bottom: 1rem;
  }
  .login-page form > div {
    margin-bottom: 1rem;
  }
  .login-page h2 {
  text-align: center;
}
  </style>
  