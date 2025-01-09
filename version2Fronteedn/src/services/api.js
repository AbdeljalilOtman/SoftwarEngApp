import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080', // your Spring Boot server URL
  headers: {
    'Content-Type': 'application/json',
  },
});

// Optionally add an interceptor to attach token from localStorage
instance.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

export default instance;
