import { mount } from '@vue/test-utils'
import Login from '@/pages/Login.vue'
import { createStore } from 'vuex'
import router from '@/router' // If you want to test actual routing
import flushPromises from 'flush-promises'

describe('Login.vue', () => {
  let actions
  let store

  beforeEach(() => {
    actions = {
      loginUser: jest.fn()
    }
    store = createStore({
      actions
    })
  })

  test('renders login form', () => {
    const wrapper = mount(Login, {
      global: {
        plugins: [store, router]
      }
    })
    expect(wrapper.find('h2').text()).toBe('Login')
    expect(wrapper.find('form').exists()).toBe(true)
  })

  test('submits form and calls login fetch', async () => {
    global.fetch = jest.fn(() =>
      Promise.resolve({
        status: 200,
        ok: true,
        json: () => Promise.resolve({ user: { id: 123 } })
      })
    )

    const wrapper = mount(Login, {
      global: {
        plugins: [store, router]
      }
    })

    // Fill in inputs
    const emailInput = wrapper.find('input[type="email"]')
    await emailInput.setValue('test@example.com')
    const passwordInput = wrapper.find('input[type="password"]')
    await passwordInput.setValue('mypassword')

    // Submit form
    await wrapper.find('form').trigger('submit.prevent')
    await flushPromises() // Wait for promises to resolve

    // Check if fetch was called with correct URL
    expect(global.fetch).toHaveBeenCalledWith(
      expect.stringContaining('test%40example.com')
    )
    // Check if loginUser action was called
    expect(actions.loginUser).toHaveBeenCalledWith(expect.any(Object), {
      user: { id: 123 }
    })

    // Check if it routed to /dashboard/123
    expect(wrapper.vm.$router.currentRoute.value.path).toBe('/dashboard/123')
  })

  test('shows error message on 401 response', async () => {
    global.fetch = jest.fn(() =>
      Promise.resolve({
        status: 401,
        ok: false
      })
    )

    const wrapper = mount(Login, {
      global: {
        plugins: [store, router]
      }
    })

    // Fill in inputs
    await wrapper.find('input[type="email"]').setValue('baduser@example.com')
    await wrapper.find('input[type="password"]').setValue('wrongpassword')

    // Submit form
    await wrapper.find('form').trigger('submit.prevent')
    await flushPromises()

    expect(wrapper.text()).toContain('Invalid email or password')
  })
})