<template>
  <div class="dashboard-page">
    <h1>Welcome to Your Dashboard</h1>
    <p>
      This is your main hub. From here, you can manage your account, adjust settings, and
      see updates about your job applications (in the future).
    </p>

    <!-- Logout Button -->
    <button class="logout-btn" @click="logoutUser">Logout</button>
  </div>
</template>

<script>
export default {
  name: 'Dashboard',
  props: {
    userId: {
      type: String,
      required: true
    }
  },
  mounted() {
    console.log('User ID:', this.userId);
    // Fetch data specific to this user if needed
  },
  methods: {
    logoutUser() {
      // Dispatch the logout action from the Vuex store
      this.$store.dispatch('logout');
      // Redirect user to the login page after logging out
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
.dashboard-page {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.logout-btn {
  background-color: #f44336; /* Red button color */
  color: #fff;
  padding: 0.6rem 1.2rem;
  border-radius: 4px;
  border: none;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.2s;
}

.logout-btn:hover {
  background-color: #d32f2f;
}
</style>

  
 
  