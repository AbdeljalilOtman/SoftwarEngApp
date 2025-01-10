import { mount } from '@vue/test-utils'
import CompanyRegister from '@/pages/CompanyRegister.vue'
import { createStore } from 'vuex'
import router from '@/router'
import flushPromises from 'flush-promises'

describe('CompanyRegister.vue', () => {
  let store
  let actions

  beforeEach(() => {
    actions = {
      registerCompany: jest.fn(() =>
        Promise.resolve({ user: { id: 999 } })
      )
    }
    store = createStore({
      actions
    })
  })

  test('renders company registration form', () => {
    const wrapper = mount(CompanyRegister, {
      global: {
        plugins: [store, router]
      }
    })
    expect(wrapper.find('h2').text()).toBe('Company Registration')
  })

  test('submits form and dispatches registerCompany', async () => {
    const wrapper = mount(CompanyRegister, {
      global: {
        plugins: [store, router]
      }
    })

    await wrapper.find('input[type="text"]').setValue('My Company')
    await wrapper.find('input[type="email"]').setValue('company@example.com')
    await wrapper.find('input[type="password"]').setValue('password123')

    await wrapper.find('form').trigger('submit.prevent')
    await flushPromises()

    expect(actions.registerCompany).toHaveBeenCalledWith(
      expect.any(Object),
      {
        companyName: 'My Company',
        email: 'company@example.com',
        password: 'password123'
      }
    )

    // After registration, it should navigate to /dashboard/999 or /company-dashboard/999 (adjust accordingly)
    expect(wrapper.vm.$router.currentRoute.value.path).toBe('/dashboard/999')
  })
})