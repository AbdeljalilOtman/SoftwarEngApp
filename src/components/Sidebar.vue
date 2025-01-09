<!-- src/components/Sidebar.vue -->
<template>
    <div class="sidebar">
      <nav>
        <ul>
          <!-- Dashboard/Home link -->
          <li>
            <router-link to="/dashboard">
              <i class="fas fa-home"></i>
              <span>Dashboard</span>
            </router-link>
          </li>
          <!-- Account link -->
          <li>
            <router-link to="/account">
              <i class="fas fa-user"></i>
              <span>Account</span>
            </router-link>
          </li>
          <!-- Settings link -->
          <li>
            <router-link to="/settings">
              <i class="fas fa-cog"></i>
              <span>Settings</span>
            </router-link>
          </li>
        </ul>
      </nav>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Sidebar'
  };
  </script>
  
  <style scoped>
  .sidebar {
    width: 240px;
    background-color: #fff;
    border-right: 1px solid #ccc;
    padding: 1rem;
    min-height: 100vh;
  }
  
  .sidebar ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }
  
  .sidebar li {
    margin-bottom: 1rem;
  }
  
  .sidebar a {
    display: flex;
    align-items: center;
    gap: 0.5rem; /* space between icon and text */
    color: #333;
    font-weight: 500;
    text-decoration: none;
    padding: 0.5rem;
    border-radius: 4px;
    transition: background-color 0.2s;
  }
  
  .sidebar a:hover {
    background-color: #f2f2f2;
  }
  </style>
  