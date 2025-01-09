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
      <div v-if="error" class="error-message">
        {{ error }}
      </div>
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
    password: '',
    error: null
  };
},
methods: {
  ...mapActions(['loginUser']),
  async login() {
    this.error = null;
    try {
      const response = await fetch(`http://localhost:8080/api/job-seekers/login?email=${encodeURIComponent(this.email)}&password=${encodeURIComponent(this.password)}`);
      
      if (response.status === 401) {
        throw new Error('Invalid email or password');
      }

      if (!response.ok) {
        throw new Error('Login failed');
      }

      const userData = await response.json();
      console.log('Login successful:', userData);
      await this.loginUser(userData);
      this.$router.push(`/dashboard/${userData.user.id}`);
    } catch (error) {
      console.error('Login error:', error);
      this.error = error.message;
    }
  }
}
};
</script>

<style scoped>
.error-message {
color: red;
margin-top: 1rem;
text-align: center;
}
</style>  