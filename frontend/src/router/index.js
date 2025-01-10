import { createRouter, createWebHistory } from 'vue-router';
import MainLayout from '@/layouts/MainLayout.vue';
import Login from '@/pages/Login.vue';
import Register from '@/pages/Register.vue';
import Dashboard from '@/pages/Dashboard.vue';
import Account from '@/pages/Account.vue';
import Settings from '@/pages/Settings.vue';
import LandingPage from '@/pages/LandingPage.vue';
import CompanyLogin from '@/pages/CompanyLogin.vue';
import CompanyRegister from '@/pages/CompanyRegister.vue';
import CompanyDashboard from '@/pages/CompanyDashboard.vue';
import CreateJobPost from '@/pages/CreateJobPost.vue';
import CompanyAccount from '@/pages/CompanyAccount.vue';

// Example auth guard
function requireAuth(to, from, next) {
  const isAuthenticated = localStorage.getItem('token');
  if (!isAuthenticated) {
    next('/login');
  } else {
    next();
  }
}
function requireCompanyAuth(to, from, next) {
  const companyToken = localStorage.getItem('token');
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
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  {
    path: '/dashboard/:userId',
    name: 'Dashboard',
    component: Dashboard,
    props: true,
    beforeEnter: requireAuth,
  },
  {
    path: '/account/:userId',
    name: 'Account',
    component: Account,
    props: true,
    beforeEnter: requireAuth, // Protect this route
  },
  {
    path: '/company-register',
    component: CompanyRegister,
  },
  {
    path: '/company-login',
    component: CompanyLogin,
  },
  {
    path: '/create-job/:id',
    name: 'CreateJobPost',
    component: CreateJobPost,
    beforeEnter: requireCompanyAuth,
  },
  {
    path: '/company-account/:userId',
    name: 'CompanyAccount',
    component: CompanyAccount,
    props: true,
    beforeEnter: requireCompanyAuth,
  },
  
  {
    path: '/company-dashboard/:id',
    component: CompanyDashboard,
    beforeEnter: requireCompanyAuth,
  },  
  {
    path: '/',
    component: MainLayout,
    children: [
      {
        path: 'settings',
        name: 'Settings',
        component: Settings,
        beforeEnter: requireAuth, // Protect this route
      },
    ],
  },
];

export default createRouter({
  history: createWebHistory(),
  routes,
});
