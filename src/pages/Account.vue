<template>
    <div class="account-page">
      <div class="header-row">
        <h2>My Account</h2>
        <router-link to="/dashboard" class="btn-link">Back to Dashboard</router-link>
      </div>
  
      <form @submit.prevent="updateAccount" class="account-form">
        <div>
          <label>Name:</label>
          <input v-model="name" type="text" />
        </div>
        <div>
          <label>Email:</label>
          <input v-model="email" type="email" disabled />
        </div>
        <div>
          <label>Field of Expertise:</label>
          <input v-model="fieldOfInterest" type="text" />
        </div>
        <div>
          <label>Institution Name:</label>
          <input v-model="institutionName" type="text" />
        </div>
        <div>
          <label>Program Name:</label>
          <input v-model="programName" type="text" />
        </div>
        <div>
          <label>Resume URL:</label>
          <input v-model="resumeUrl" type="text" />
        </div>
        <div>
          <label>Portfolio URL:</label>
          <input v-model="portfolioUrl" type="text" />
        </div>
  
        <button type="submit">Save Changes</button>
      </form>
    </div>
  </template>
  
  <script>
  import { mapState } from 'vuex';
  import api from '@/services/api.js'; // <-- use custom axios instance
  
  export default {
    name: 'Account',
    data() {
      return {
        name: '',
        email: '',
        fieldOfInterest: '',
        institutionName: '',
        programName: '',
        resumeUrl: '',
        portfolioUrl: '',
      };
    },
    computed: {
      ...mapState(['user'])
    },
    mounted() {
      if (this.user) {
        this.name = this.user.name;
        this.email = this.user.email;
        this.fieldOfInterest = this.user.fieldOfInterest;
        this.institutionName = this.user.institutionName;
        this.programName = this.user.programName;
        this.resumeUrl = this.user.resumeUrl;
        this.portfolioUrl = this.user.portfolioUrl;
      }
    },
    methods: {
      async updateAccount() {
        try {
          if (!this.user || !this.user.id) {
            alert("User not found in store. Can't update.");
            return;
          }
  
          const payload = {
            name: this.name,
            fieldOfInterest: this.fieldOfInterest,
            institutionName: this.institutionName,
            programName: this.programName,
            resumeUrl: this.resumeUrl,
            portfolioUrl: this.portfolioUrl
          };
  
          // Example endpoint: PUT /api/jobseekers/:id
          const response = await api.put(`/api/jobseekers/${this.user.id}`, payload);
          // Potentially update the store with new user data: 
          // this.$store.commit('SET_USER', response.data);
  
          alert('Account updated successfully!');
        } catch (error) {
          console.error('Update Account Error:', error);
          alert('Failed to update account. Check console for details.');
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .account-page {
    display: flex;
    flex-direction: column;
    gap: 2rem;
  }
  
  .header-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  
  .account-form {
    max-width: 400px;
    display: flex;
    flex-direction: column;
    gap: 1rem;
  }
  
  /* Example styling for the "Back to Dashboard" link */
  .btn-link {
    background-color: #4caf50;
    color: #fff !important;
    padding: 0.6rem 1.2rem;
    border-radius: 4px;
    text-decoration: none;
    transition: background-color 0.2s;
  }
  .btn-link:hover {
    background-color: #43a047;
    color: #fff;
  }
  </style>
  