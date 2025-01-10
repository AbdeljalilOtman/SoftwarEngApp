<template>
  <div class="dashboard-page">
    <!-- Navigation Bar -->
    <nav class="navbar">
      <h1 class="logo">JobMatrix</h1>
      <div class="nav-links">
        <router-link to="/account" class="nav-btn">My Account</router-link>
        <router-link to="/settings" class="nav-btn">Settings</router-link>
        <button class="logout-btn" @click="logoutUser">Logout</button>
      </div>
    </nav>

    <!-- Welcome Section -->
    <div class="welcome-section">
      <h2>Welcome to Your Dashboard</h2>
      <p>
        This is your main hub. From here, you can manage your account, adjust settings,
        and explore updates about your job applications.
      </p>
    </div>

    <!-- Quick Actions -->
    <div class="quick-actions">
      <h3>Quick Actions</h3>
      <router-link to="/account" class="quick-action-btn">Edit My Account</router-link>
      <router-link to="/settings" class="quick-action-btn">Update Settings</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "Dashboard",
  props: {
    userId: {
      type: String,
      required: true,
    },
  },
  mounted() {
    console.log("User ID:", this.userId);
    // Fetch data specific to this user if needed
  },
  methods: {
    logoutUser() {
      // Dispatch the logout action from the Vuex store
      this.$store.dispatch("logout");
      // Redirect user to the login page after logging out
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
.dashboard-page {
  display: flex;
  flex-direction: column;
  gap: 2rem;
  padding: 2rem;
  font-family: Arial, sans-serif;
}

/* Navigation Bar */
.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #4caf50;
  padding: 1rem 2rem;
  border-radius: 8px;
  color: #fff;
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
}

.nav-links {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.nav-btn {
  background-color: #43a047;
  color: #fff;
  padding: 0.6rem 1.2rem;
  border-radius: 4px;
  text-decoration: none;
  transition: background-color 0.2s;
}

.nav-btn:hover {
  background-color: #388e3c;
}

.logout-btn {
  background-color: #f44336;
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

/* Welcome Section */
.welcome-section {
  text-align: center;
}

.quick-actions {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
}

.quick-actions h3 {
  margin-bottom: 1rem;
}

.quick-action-btn {
  background-color: #4caf50;
  color: #fff;
  padding: 0.8rem 1.5rem;
  border-radius: 4px;
  text-decoration: none;
  transition: background-color 0.2s;
}

.quick-action-btn:hover {
  background-color: #43a047;
}
</style>
