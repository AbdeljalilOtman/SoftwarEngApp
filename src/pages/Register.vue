<template>
  <div class="register-page container-small">
    <h2 class="mb-2">Register</h2>
    <form @submit.prevent="register">
      <div>
        <label>Name:</label>
        <input v-model="name" type="text" required />
      </div>
      <div>
        <label>Email:</label>
        <input v-model="email" type="email" required />
      </div>
      <div>
        <label>Password:</label>
        <input v-model="password" type="password" required />
      </div>
      <div>
        <label>Field of Expertise:</label>
        <input v-model="fieldOfInterest" type="text" />
      </div>
      <button type="submit">Register</button>
    </form>
    <p class="mt-2">
      Already have an account? <router-link to="/login">Login here</router-link>
    </p>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: "Register",
  data() {
    return {
      name: "",
      email: "",
      password: "",
      fieldOfInterest: "",
    };
  },
  methods: {
    async register() {
      try {
        // Register the user
        const response = await this.$store.dispatch('registerUser', {
          name: this.name,
          email: this.email,
          password: this.password,
          fieldOfInterest: this.fieldOfInterest,
        });

        // After successful registration, redirect to dashboard or home page
        alert("Registration successful!");
        this.$router.push(`/dashboard/${response.user.id}`); // Redirect after successful registration
      } catch (error) {
        console.error("Registration failed:", error);
        alert("Registration failed. Please try again.");
      }
    },
  },
};
</script>

<style scoped>
.register-page h2 {
  margin-bottom: 1rem;
}
.register-page form > div {
  margin-bottom: 1rem;
}
</style>
