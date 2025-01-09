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
import api from '@/services/api.js';

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
  async created() {
    try {
      const userId = this.$route.params.userId;
      if (!userId) {
        console.error('User ID is missing from the route');
        return;
      }
      
      const response = await api.get(`http://localhost:8080/api/job-seekers/${userId}`);
      const userData = response.data;

      // Populate form fields
      this.name = userData.name || '';
      this.email = userData.email || '';
      this.fieldOfInterest = userData.fieldOfInterest || '';
      this.institutionName = userData.institutionName || '';
      this.programName = userData.programName || '';
      this.resumeUrl = userData.resumeUrl || '';
      this.portfolioUrl = userData.portfolioUrl || '';
    } catch (error) {
      console.error('Error loading user data:', error);
    }
  },
  methods: {
    async updateAccount() {
      try {
        const userId = this.$route.params.userId;
        if (!userId) {
          alert("User ID is missing. Can't update.");
          return;
        }

        const payload = {
          name: this.name || null,
          fieldOfInterest: this.fieldOfInterest || null,
          institutionName: this.institutionName || null,
          programName: this.programName || null,
          resumeUrl: this.resumeUrl || null,
          portfolioUrl: this.portfolioUrl || null,
        };

        await api.put(`http://localhost:8080/api/job-seekers/${userId}`, payload);
        alert('Account updated successfully!');
      } catch (error) {
        console.error('Update Account Error:', error);
        alert('Failed to update account. Check console for details.');
      }
    },
  },
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
