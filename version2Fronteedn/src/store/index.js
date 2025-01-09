import { createStore } from 'vuex';
import api from '@/services/api.js'; // changed from `axios`

export default createStore({
  state: {
    user: null,
    token: null,
    isAuthenticated: false,
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user;
    },
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_AUTHENTICATED(state, isAuth) {
      state.isAuthenticated = isAuth;
    }
  },
  actions: {
    async registerUser({ commit }, payload) {
      try {
        // Use custom instance
        const { data } = await api.post('/api/jobseekers/register', payload);
        commit('SET_USER', data.user);
        commit('SET_TOKEN', data.token);
        commit('SET_AUTHENTICATED', true);
        localStorage.setItem('token', data.token);
      } catch (error) {
        console.error('Registration Error:', error);
        throw error;
      }
    },
    async loginUser({ commit }, payload) {
      try {
        // Use custom instance
        const { data } = await api.post('/api/login', payload);
        commit('SET_USER', data.user);
        commit('SET_TOKEN', data.token);
        commit('SET_AUTHENTICATED', true);
        localStorage.setItem('token', data.token);
      } catch (error) {
        console.error('Login Error:', error);
        throw error;
      }
    },
    logout({ commit }) {
      commit('SET_USER', null);
      commit('SET_TOKEN', null);
      commit('SET_AUTHENTICATED', false);
      localStorage.removeItem('token');
    }
  },
  getters: {
    getUser(state) {
      return state.user;
    },
    isAuthenticated(state) {
      return state.isAuthenticated;
    },
  }
});
