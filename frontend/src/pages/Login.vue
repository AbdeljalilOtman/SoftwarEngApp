<template>
  <div class="login-page">
    <!-- Background Image -->
    <div class="background-image"></div>

    <div class="content">
      <img src="@/img/jobseeker.png" alt="Company Logo" class="company-logo" />

      <h2> Login</h2>

      <!-- Form -->
      <form @submit.prevent="login" class="login-form">
        <div class="input-field">
          <label for="email">Email:</label>
          <input
            id="email"
            v-model="email"
            type="email"
            required
            placeholder="Enter your email"
          />
        </div>

        <div class="input-field">
          <label for="password">Password:</label>
          <div class="password-wrapper">
            <input
              id="password"
              v-model="password"
              :type="passwordVisible ? 'text' : 'password'"
              required
              placeholder="Enter your password"
            />
            <button
              type="button"
              @click="togglePasswordVisibility"
              @mouseover="eyeButtonHover(true)"
              @mouseleave="eyeButtonHover(false)"
              :class="{ 'eye-button-hover': isEyeButtonHovered }"
              class="eye-button"
            >
              👁
            </button>
          </div>
        </div>

        <button type="submit" class="submit-btn">Login</button>
      </form>

      <p class="register-link">
        Don't have a company account? <router-link to="/register">Register</router-link>
      </p>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
name: 'Login',
data() {
  return {
    email: '',
    password: '',
    error: null
  };
},
methods: {
  ...mapActions(['loginUser']),
  async login() {
    this.error = null;
    try {
      const response = await fetch(`http://localhost:8080/api/job-seekers/login?email=${encodeURIComponent(this.email)}&password=${encodeURIComponent(this.password)}`);
      
      if (response.status === 401) {
        throw new Error('Invalid email or password');
      }

      if (!response.ok) {
        throw new Error('Login failed');
      }

      const userData = await response.json();
      console.log('Login successful:', userData);
      this.$router.push(`/dashboard/${userData.user.id}`);
      await this.loginUser(userData);
      
    } catch (error) {
      console.error('Login error:', error);
      this.error = error.message;
    }
  },
  togglePasswordVisibility() {
      this.passwordVisible = !this.passwordVisible;
    },
    eyeButtonHover(isHovered) {
      this.isEyeButtonHovered = isHovered;
    }
}
};
</script>

<style scoped>
/* Background */
.login-page {
  position: relative;
  height: 100vh;
  background-image: url('@/img/background.jpg');
  background-size: cover;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
}

.background-image {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5); /* Semi-transparent overlay */
  z-index: -1;
}

/* Content */
.content {
  background: rgba(255, 255, 255, 0.3); /* Glassmorphism effect */
  border-radius: 15px;
  padding: 2rem 3rem;
  backdrop-filter: blur(10px); /* Glass effect */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 80%;
  max-width: 600px;
}

h2 {
  font-size: 2.5rem;
  margin-bottom: 1rem;
  font-weight: bold;
}

.company-logo {
  width: 150px;
  height: 150px;
  object-fit: contain;
  margin-bottom: 1rem;
  animation: fadeIn 1s ease-in-out;
}

/* Form */
.login-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
  margin-top: 2rem;
}

.input-field {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

label {
  font-size: 1.1rem;
  color: white;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 1rem;
  background: transparent; /* Transparent background */
  border: 1px solid white; /* White border */
  border-radius: 12px;
  font-size: 1.1rem;
  color: white;
  outline: none;
  transition: background 0.3s ease, border-color 0.3s ease;
}

input::placeholder {
  color: white;
}

input:hover {
  background: rgba(255, 255, 255, 0.2); /* Slight background change on hover */
  border-color: #00bfff; /* Change border color on hover */
}

input:focus {
  background: rgba(255, 255, 255, 0.3); /* Slight background change on focus */
  border-color: #00bfff; /* Change border color on focus */
}

.password-wrapper {
  position: relative;
  width: 100%;
}

.eye-button {
  position: absolute;
  right: 10px;
  top: 0%;

  background: transparent;
  border: none;
  color: white;
  font-size: 1.5rem;
  cursor: pointer;
  transition: transform 0.3s ease, background-color 0.3s ease;
  
}

.eye-button-hover {
  transform: scale(1.2); /* Scale the eye button on hover */
  color: #00bfff; /* Change color on hover */
}

.submit-btn {
  padding: 1rem 2rem;
  background-color: rgba(0, 123, 255, 0.8); /* Blue background */
  border: none;
  border-radius: 12px;
  font-size: 1.1rem;
  color: white;
  cursor: pointer;
  transition: transform 0.3s ease, background-color 0.3s ease;
}

.submit-btn:hover {
  transform: scale(1.05);
  background-color: rgba(0, 123, 255, 1);
}

.submit-btn:active {
  transform: scale(1);
}

.register-link {
  margin-top: 2rem;
  font-size: 1.1rem;
}

.register-link a {
  color: #007bff; /* Dark blue */
  text-decoration: none;
}

.register-link a:hover {
  text-decoration: underline;
}

/* Fade In Animation for Company Logo */
@keyframes fadeIn {
  0% {
    opacity: 0;
    transform: translateY(-20px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>