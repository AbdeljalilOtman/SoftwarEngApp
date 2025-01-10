<!-- src/layouts/MainLayout.vue -->
<template>
    <div class="main-layout">
      <Sidebar />
  
      <!-- Main content area -->
      <div class="main-content">
        <!-- The child components (Dashboard/Account/Settings) render here -->
        <router-view />
      </div>
    </div>
  </template>
  
  <script>
  import Sidebar from '@/components/Sidebar.vue';
  
  export default {
    name: 'MainLayout',
    components: {
      Sidebar
    }
  };
  </script>
  
  <style scoped>
  .main-layout {
    display: flex;
    min-height: 100vh; /* Fill the viewport height */
  }
  
  .main-content {
    flex: 1;
    padding: 2rem;
    background-color: #fff;
  }
  </style>
  