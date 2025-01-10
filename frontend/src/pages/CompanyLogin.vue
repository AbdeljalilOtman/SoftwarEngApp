<template>
    <div class="company-login-page container-small">
      <h2>Company Login</h2>
      <form @submit.prevent="loginCompany">
        <div>
          <label>Company Email:</label>
          <input v-model="email" type="email" required />
        </div>
        <div>
          <label>Password:</label>
          <input v-model="password" type="password" required />
        </div>
        <button type="submit">Login</button>
      </form>
  
      <p class="mt-2">
        Don't have a company account?
        <router-link to="/company-register">Register here</router-link>
      </p>
    </div>
  </template>
  
  <script>
  import api from "@/services/api.js";
  import { mapActions } from 'vuex';
  
  export default {
    name: "CompanyLogin",
    data() {
      return {
        email: "",
        password: "",
      };
    },
    methods: {
      ...mapActions(['loginUser']),
      async loginCompany() {
  try {
    // Example backend endpoint: POST /api/recruiters/login
    const response = await fetch(`http://localhost:8080/api/recruiter/login?email=${encodeURIComponent(this.email)}&password=${encodeURIComponent(this.password)}`);

    if (response.status === 401) {
      throw new Error('Invalid email or password');
    }

    const userData = await response.json();
    console.log('Login successful:', userData);

    // Pass user data to Vuex if needed
    await this.loginUser(userData);

    // Navigate to the dashboard with the recruiter ID
    const recruiterId = userData.user.id;
    this.$router.push(`/company-dashboard/${recruiterId}`);
  } catch (error) {
    console.error("Company login failed:", error);
    alert("Company login failed. Please try again.");
  }
}

    },
  };
  </script>
  
  <style scoped>
  .company-login-page h2 {
    margin-bottom: 1rem;
    text-align: center;
  }
  .company-login-page form > div {
    margin-bottom: 1rem;
  }
  </style>
  