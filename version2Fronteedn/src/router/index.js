// src/router/index.js

import { createRouter, createWebHistory } from 'vue-router';
import LandingPage from '@/pages/LandingPage.vue';
import Login from '@/pages/Login.vue';
import Register from '@/pages/Register.vue';
import Dashboard from '@/pages/Dashboard.vue';
import Account from '@/pages/Account.vue';
import Settings from '@/pages/Settings.vue';
import CompanyLogin from '@/pages/CompanyLogin.vue';
import CompanyRegister from '@/pages/CompanyRegister.vue';
import CompanyDashboard from '@/pages/CompanyDashboard.vue';
import CreateJobPost from '@/pages/CreateJobPost.vue';
import CompanyAccount from '@/pages/CompanyAccount.vue';

import MainLayout from '@/layouts/MainLayout.vue';
// Example auth guard (for Job Seekers)
function requireAuth(to, from, next) {
  const isAuthenticated = localStorage.getItem('token');
  if (!isAuthenticated) {
    next('/login');
  } else {
    next();
  }
}

// If you want a separate guard for companies:
function requireCompanyAuth(to, from, next) {
  const companyToken = localStorage.getItem('companyToken');
  if (!companyToken) {
    next('/company-login');
  } else {
    next();
  }
}

const routes = [
  { 
    path: '/',
    component: LandingPage, // Our new landing page
  },
  { 
    path: '/login',
    component: Login,
  },
  { 
    path: '/register',
    component: Register,
  },

  // Existing main layout for job seekers
  {
    path: '/',
    component: MainLayout,
    // beforeEnter: requireAuth, // If you want these routes protected for job seekers
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: Dashboard,
      },
      {
        path: 'account',
        name: 'Account',
        component: Account,
      },
      {
        path: 'settings',
        name: 'Settings',
        component: Settings,
      },
    ],
  },

  // New routes for Companies
  {
    path: '/company-login',
    component: CompanyLogin,
  },
  {
    path: '/company-register',
    component: CompanyRegister,
  },
  {
    path: '/company-dashboard',
    component: CompanyDashboard,
    // beforeEnter: requireCompanyAuth,
  },
  {
    path: '/create-job-post',
    name: 'CreateJobPost',
    component: CreateJobPost,
    // beforeEnter: requireCompanyAuth,
  },
  {
    path: '/company-account',
    name: 'CompanyAccount',
    component: CompanyAccount,
    // beforeEnter: requireCompanyAuth,
  },
];

export default createRouter({
  history: createWebHistory(),
  routes,
});
