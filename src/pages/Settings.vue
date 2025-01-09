<!-- src/pages/Settings.vue -->
<template>
    <div class="settings-page">
      <div class="header-row">
        <h2>Settings</h2>
      </div>
  
      <div class="settings-section">
        <h3>Notifications</h3>
        <!-- Example toggles for notifications or preferences -->
        <label>
          <input type="checkbox" v-model="enableEmail" />
          Enable Email Notifications
        </label>
        <label>
          <input type="checkbox" v-model="enableSMS" />
          Enable SMS Notifications
        </label>
      </div>

      <div class="settings-section">
      <h3>Account</h3>
      <button class="btn-logout" @click="logout">Logout</button>
    </div>

    </div>
  </template>
  
  <script>
  export default {
    name: 'Settings',
    data() {
      return {
        themeColor: 'light',
        enableEmail: true,
        enableSMS: false
      };
    },
    methods: {
    logout() {
      // Clear authentication token (and any other data)
      localStorage.removeItem("token");

      // Redirect to the login page
      this.$router.push("/login");
    }
  }
};
  </script>
  
  <style scoped>
  .settings-page {
    display: flex;
    flex-direction: column;
    gap: 2rem;
  }
  
  .header-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  
  .settings-section {
    margin-top: 1rem;
  }
  
  .btn-logout {
  background-color: #e53935;
  color: #fff;
  padding: 0.6rem 1.2rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.btn-logout:hover {
  background-color: #d32f2f;
}
  </style>
  