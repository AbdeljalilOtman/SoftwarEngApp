<template>
    <div class="company-account-page container-small">
      <div class="header-row">
        <h2>Company Account Settings</h2>
        <router-link to="/company-dashboard" class="btn-back">Back to Dashboard</router-link>
      </div>
      <form @submit.prevent="updateAccount">
        <div>
          <label>Company Name:</label>
          <input v-model="companyName" type="text" required />
        </div>
  
        <div>
          <label>Representative Name:</label>
          <input v-model="representativeName" type="text" required />
        </div>
  
        <div>
          <label>Company Email:</label>
          <input v-model="email" type="email" required />
        </div>
  
        <div>
          <label>Password:</label>
          <input v-model="password" type="password" />
        </div>
  
        <div>
          <label>Company Description:</label>
          <textarea v-model="companyDescription" rows="2"></textarea>
        </div>
  
        <div>
          <label>Company Logo URL:</label>
          <input v-model="companyLogoUrl" type="text" />
        </div>
  
        <button type="submit">Save Changes</button>
      </form>
    </div>
  </template>
  
  <script>
  import api from "@/services/api.js";
  
  export default {
    name: "CompanyAccount",
    data() {
      return {
        companyName: "",
        representativeName: "",
        email: "",
        password: "",
        companyDescription: "",
        companyLogoUrl: "",
      };
    },
    async mounted() {
      try {
        const res = await api.get("/api/recruiters/me");
        const recruiter = res.data;
        this.companyName = recruiter.companyName || "";
        this.representativeName = recruiter.representativeName || "";
        this.email = recruiter.email || "";
        this.password = "";
        this.companyDescription = recruiter.companyDescription || "";
        this.companyLogoUrl = recruiter.companyLogoUrl || "";
      } catch (error) {
        console.error("Error fetching company account details:", error.response || error);
      }
    },
    methods: {
      async updateAccount() {
        try {
          const payload = {
            companyName: this.companyName,
            representativeName: this.representativeName,
            email: this.email,
            password: this.password,
            companyDescription: this.companyDescription,
            companyLogoUrl: this.companyLogoUrl,
          };
          const response = await api.put("/api/recruiters/me", payload);
          console.log("Update recruiter response:", response.data);
          alert("Company account updated successfully!");
        } catch (error) {
          console.error("Error updating company account:", error.response || error);
          alert("Failed to update. Check console for details.");
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .company-account-page {
    margin-top: 2rem;
  }
  .header-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 1rem;
  }
  .btn-back {
    background-color: #4caf50;
    color: #fff !important;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    text-decoration: none;
    transition: background-color 0.2s;
  }
  .btn-back:hover {
    background-color: #43a047;
  }
  .company-account-page form > div {
    margin-bottom: 1rem;
  }
  </style>
  