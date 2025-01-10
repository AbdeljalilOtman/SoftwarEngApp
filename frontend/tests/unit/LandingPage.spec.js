import { mount } from '@vue/test-utils'
import LandingPage from '@/pages/LandingPage.vue'
import { createRouter, createWebHistory } from 'vue-router'

describe('LandingPage.vue', () => {
  // If we want to test actual routing, we can create a test router
  const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/login', name: 'Login' },
      { path: '/company-login', name: 'CompanyLogin' }
    ]
  })

  test('renders welcome message', async () => {
    const wrapper = mount(LandingPage, {
      global: {
        plugins: [router]
      }
    })

    expect(wrapper.text()).toContain('Welcome to JobMatrix')
    expect(wrapper.text()).toContain('Please choose your account type:')
  })

  test('goToJobSeeker button navigates to /login', async () => {
    const wrapper = mount(LandingPage, {
      global: {
        plugins: [router]
      }
    })

    await router.isReady()

    const jobSeekerButton = wrapper.find('button:nth-child(1)') 
    await jobSeekerButton.trigger('click')
    // Wait for the router to handle navigation
    expect(router.currentRoute.value.path).toBe('/login')
  })

  test('goToCompany button navigates to /company-login', async () => {
    const wrapper = mount(LandingPage, {
      global: {
        plugins: [router]
      }
    })

    await router.isReady()

    const companyButton = wrapper.find('button:nth-child(2)')
    await companyButton.trigger('click')
    expect(router.currentRoute.value.path).toBe('/company-login')
  })
})