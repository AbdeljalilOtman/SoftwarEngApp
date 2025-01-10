<template>
  <div class="register-page">
    <div class="register-container">
      <div class="form-container">
        <img src="@/img/jobseeker.png" alt="Company Logo" class="company-logo" />
        <h2>Register</h2>
        <form @submit.prevent="register">
          <div class="input-group">
            <label for="company-name">Name:</label>
            <input v-model="name" type="text" id="company-name" required />
          </div>
          <div class="input-group">
            <label for="email">Email:</label>
            <input v-model="email" type="email" id="email" required />
          </div>
          <div class="input-group">
            <label for="password">Password:</label>
            <input v-model="password" type="password" id="password" required />
          </div>
          <div class="input-group">
            <label for="fieldOfInterest">Field of Interest:</label>
            <input v-model="fieldOfInterest" type="text" id="fieldOfInterest" required />
          </div>
          <button type="submit">Register</button>
        </form>
        <p class="login-link">
          Return to Login Page, <router-link to="/login">Login</router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: "Register",
  data() {
    return {
      name: "",
      email: "",
      password: "",
      fieldOfInterest: "",
    };
  },
  methods: {
    async register() {
      try {
        // Register the user
        const response = await this.$store.dispatch('registerUser', JSON.stringify({
          name: this.name,
          email: this.email,
          password: this.password,
          fieldOfInterest: this.fieldOfInterest,
        }));

        // After successful registration, redirect to dashboard or home page
        alert("Registration successful!");
        this.$router.push(`/dashboard/${response.user.id}`); // Redirect after successful registration
      } catch (error) {
        console.error("Registration failed:", error);
        alert("Registration failed. Please try again.");
      }
    },
  },
};
</script>

<style scoped>
.register-page {
  position: relative;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url('@/img/background.jpg') no-repeat center center fixed;
  background-size: cover;
}

.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.6);
}

.form-container {
  background: rgba(255, 255, 255, 0.3);
  padding: 1.5rem 2rem; /* Reduced padding */
  border-radius: 10px;
  backdrop-filter: blur(10px);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
  text-align: center;
  animation: slideIn 0.5s ease-out;
  width: 100%;
  max-width: 380px; /* Reduced width */
}

/* Logo */
.company-logo {
  max-width: 120px; /* Smaller logo */
  margin-bottom: 15px; /* Reduced margin */
}

h2 {
  font-size: 1.8rem; /* Reduced font size */
  color: white;
  margin-bottom: 1rem; /* Reduced margin */
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* Input Fields */
.input-group {
  margin-bottom: 1rem; /* Reduced margin */
}

label {
  color: white;
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 0.8rem; /* Reduced padding */
  background: transparent;
  border: 1px solid white;
  border-radius: 5px;
  font-size: 1rem;
  margin-bottom: 0.5rem;
  color: white;
  outline: none;
  transition: background-color 0.3s ease, border-color 0.3s ease;
}

input::placeholder {
  color: white;
}

/* Hover effect on input fields */
input:hover {
  background-color: rgba(255, 255, 255, 0.2);
  border-color: #00bfff;
}

/* Focus effect on input fields */
input:focus {
  background-color: rgba(255, 255, 255, 0.3);
  border-color: #00bfff;
}

/* Button styling */
button {
  width: 100%;
  padding: 1rem;
  background-color: rgba(0, 123, 255, 0.8);
  color: white;
  border: 1px solid white;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
  font-size: 1.2rem;
}

button:hover {
  background-color: rgba(0, 123, 255, 1);
  transform: scale(1.05);
}

button:active {
  transform: scale(1);
}

/* Register link */
.login-link {
  color: white;
  margin-top: 1rem;
}

.login-link a {
  color: #007bff;
  text-decoration: none;
}

.login-link a:hover {
  text-decoration: underline;
}

/* Slide-in animation */
@keyframes slideIn {
  0% {
    transform: translateY(-50px);
    opacity: 0;
  }
  100% {
    transform: translateY(0);
    opacity: 1;
  }
}
</style>
