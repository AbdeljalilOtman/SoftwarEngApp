<template>
    <div class="company-register-page container-small">
      <h2>Company Registration</h2>
      <form @submit.prevent="registerCompany">
        <div>
          <label>Company Name:</label>
          <input v-model="companyName" type="text" required />
        </div>
        <div>
          <label>Company Email:</label>
          <input v-model="email" type="email" required />
        </div>
        <div>
          <label>Password:</label>
          <input v-model="password" type="password" required />
        </div>
        <button type="submit">Register</button>
      </form>
      <p class="mt-2">
        Already have a company account?
        <router-link to="/company-login">Login here</router-link>
      </p>
    </div>
  </template>
  
  <script>
  import api from "@/services/api.js";
  
  export default {
    name: "CompanyRegister",
    data() {
      return {
        companyName: "",
        email: "",
        password: "",
      };
    },
    methods: {
      async registerCompany() {
        try {
          // Example backend endpoint: POST /api/recruiters/register
          const response = await this.$store.dispatch('registerCompany', {
            companyName: this.companyName,
            email: this.email,
            password: this.password,
          });
          console.log("Company registration successful:", response.data);
  
          alert("Company registration successful!");
          this.$router.push(`/dashboard/${response.user.id}`); 
        } catch (error) {
          console.error("Company registration failed:", error.response || error);
          alert("Company registration failed. Please try again.");
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .company-register-page h2 {
    margin-bottom: 1rem;
    text-align: center;
  }
  .company-register-page form > div {
    margin-bottom: 1rem;
  }
  button {
    background-color: #4caf50;
    color: white;
    padding: 0.8rem 1.2rem;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  button:hover {
    background-color: #43a047;
  }
  </style>
  