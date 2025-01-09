<template>
    <div class="landing-page">
      <h1>Welcome to JobMatrix</h1>
      <p>Please choose your account type:</p>
      <div class="buttons">
        <button @click="goToJobSeeker">I am a Job Seeker</button>
        <button @click="goToCompany">I am a Company</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "LandingPage",
    methods: {
      goToJobSeeker() {
        this.$router.push("/login"); 
        // or another route if you want a dedicated JobSeeker landing
      },
      goToCompany() {
        this.$router.push("/company-login");
      },
    },
  };
  </script>
  
  <style scoped>
  .landing-page {
    text-align: center;
    margin-top: 4rem;
  }
  .buttons {
    margin-top: 2rem;
    display: flex;
    gap: 1rem;
    justify-content: center;
  }
  button {
    padding: 0.75rem 1.5rem;
    font-size: 1rem;
    cursor: pointer;
  }
  </style>
  