import { createRouter, createWebHistory } from 'vue-router';
import MainLayout from '@/layouts/MainLayout.vue';
import Login from '@/pages/Login.vue';
import Register from '@/pages/Register.vue';
import Dashboard from '@/pages/Dashboard.vue';
import Account from '@/pages/Account.vue';
import Settings from '@/pages/Settings.vue';

// Example auth guard
function requireAuth(to, from, next) {
  const isAuthenticated = localStorage.getItem('token');
  if (!isAuthenticated) {
    next('/login');
  } else {
    next();
  }
}

const routes = [
  { path: '/', redirect: '/login' },
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
