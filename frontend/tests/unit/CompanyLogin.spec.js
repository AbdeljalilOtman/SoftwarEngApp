import { mount } from '@vue/test-utils'
import CompanyLogin from '@/pages/CompanyLogin.vue'
import { createStore } from 'vuex'
import flushPromises from 'flush-promises'
import router from '@/router'

describe('CompanyLogin.vue', () => {
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

  test('renders company login form', () => {
    const wrapper = mount(CompanyLogin, {
      global: {
        plugins: [store, router]
      }
    })
    expect(wrapper.find('h2').text()).toBe('Company Login')
  })

  test('loginCompany fetches and navigates on success', async () => {
    global.fetch = jest.fn(() =>
      Promise.resolve({
        status: 200,
        json: () => Promise.resolve({ user: { id: 567 } })
      })
    )

    const wrapper = mount(CompanyLogin, {
      global: {
        plugins: [store, router]
      }
    })

    await wrapper.find('input[type="email"]').setValue('recruiter@example.com')
    await wrapper.find('input[type="password"]').setValue('pass1234')
    await wrapper.find('form').trigger('submit.prevent')
    await flushPromises()

    expect(global.fetch).toHaveBeenCalledWith(
      expect.stringContaining('recruiter%40example.com')
    )
    expect(actions.loginUser).toHaveBeenCalledWith(expect.any(Object), {
      user: { id: 567 }
    })

    // Should navigate to /company-dashboard/567
    expect(wrapper.vm.$router.currentRoute.value.path).toBe('/company-dashboard/567')
  })
})