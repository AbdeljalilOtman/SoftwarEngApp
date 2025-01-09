import { createStore } from 'vuex';
import api from '@/services/api.js';

export default createStore({
  state: {
    user: null,
    token: localStorage.getItem('token') || null,
    isAuthenticated: !!localStorage.getItem('token'),
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user;
    },
    SET_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token); // Save token in localStorage
    },
    SET_AUTHENTICATED(state, isAuth) {
      state.isAuthenticated = isAuth;
    },
    CLEAR_AUTH(state) {
      state.user = null;
      state.token = null;
      state.isAuthenticated = false;
      localStorage.removeItem('token'); // Clear token from localStorage
    },
  },
  actions: {
    async registerUser({ commit }, payload) {
      try {
        // Send the registration request to the backend
        const { data } = await api.post('/api/job-seekers/register', payload);
        
        // Commit user and token data to the store
        commit('SET_USER', data.user);
        commit('SET_TOKEN', data.token);
        commit('SET_AUTHENTICATED', true);

        return data; // Optionally return data to be used in the frontend
      } catch (error) {
        console.error('Registration Error:', error);
        throw error; // This will be caught in the component
      }
    },
    async loginUser({ commit }, payload) {
      try {
        commit('SET_USER', payload.user);
        commit('SET_TOKEN', payload.token);
        commit('SET_AUTHENTICATED', true);
      } catch (error) {
        console.error('Login Error:', error);
        throw error;
      }
    },
    logout({ commit }) {
      commit('CLEAR_AUTH');
    },
    initializeAuth({ commit }) {
      const token = localStorage.getItem('token');
      if (token) {
        // Optionally decode and validate the token here
        commit('SET_TOKEN', token);
        commit('SET_AUTHENTICATED', true);
      }
    },
  },
  getters: {
    getUser(state) {
      return state.user;
    },
    isAuthenticated(state) {
      return state.isAuthenticated;
    },
  },
});
