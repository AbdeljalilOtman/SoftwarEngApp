import { mount } from '@vue/test-utils'
import Register from '@/pages/Register.vue'
import { createStore } from 'vuex'
import router from '@/router'
import flushPromises from 'flush-promises'

describe('Register.vue', () => {
  let store
  let actions

  beforeEach(() => {
    actions = {
      registerUser: jest.fn(() =>
        Promise.resolve({ user: { id: 123 } })
      )
    }
    store = createStore({
      actions
    })
  })

  test('renders register form', () => {
    const wrapper = mount(Register, {
      global: {
        plugins: [store, router]
      }
    })
    expect(wrapper.find('h2').text()).toBe('Register')
  })

  test('submits form and dispatches registerUser', async () => {
    const wrapper = mount(Register, {
      global: {
        plugins: [store, router]
      }
    })

    await wrapper.find('input[type="text"]').setValue('John Doe')
    await wrapper.find('input[type="email"]').setValue('john@example.com')
    const passwordInput = wrapper.find('input[type="password"]')
    await passwordInput.setValue('securepassword')

    // Submit form
    await wrapper.find('form').trigger('submit.prevent')
    await flushPromises()

    expect(actions.registerUser).toHaveBeenCalledWith(
      expect.any(Object),
      expect.objectContaining({
        name: 'John Doe',
        email: 'john@example.com',
        password: 'securepassword'
      })
    )

    // Should redirect to /dashboard/123
    expect(wrapper.vm.$router.currentRoute.value.path).toBe('/dashboard/123')
  })
})