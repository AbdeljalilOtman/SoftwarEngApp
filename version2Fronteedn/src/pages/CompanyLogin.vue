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
  
  export default {
    name: "CompanyLogin",
    data() {
      return {
        email: "",
        password: "",
      };
    },
    methods: {
      async loginCompany() {
        try {
          // Example backend endpoint: POST /api/companies/login
          // or /api/recruiters/login if that's your actual route
          const response = await api.post("/api/recruiters/login", {
            email: this.email,
            password: this.password,
          });
          console.log("Company login successful:", response.data);
  
          // Save the token, if any
          localStorage.setItem("companyToken", response.data.token);
  
          alert("Company login successful!");
          this.$router.push("/company-dashboard");
        } catch (error) {
          console.error("Company login failed:", error.response || error);
          alert("Company login failed. Please try again.");
        }
      },
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
  