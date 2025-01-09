<!-- src/pages/Dashboard.vue -->
<template>
    <div class="dashboard-page">
      <h1>Welcome to Your Dashboard</h1>
      <p>
        This is your main hub. From here, you can manage your account, adjust settings, and
        see updates about your job applications (in the future).
      </p>
  
    </div>
  </template>
  
  <script>
  export default {
    name: 'Dashboard'
  };
  </script>
  
  <style scoped>
  .dashboard-page {
    display: flex;
    flex-direction: column;
    gap: 1rem;
  }
  
  .quick-links {
    margin-top: 2rem;
    display: flex;
    gap: 1rem;
  }
  
  /* Example button styling for router-links */
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
  