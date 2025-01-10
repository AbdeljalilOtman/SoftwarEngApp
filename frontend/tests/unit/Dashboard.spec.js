import { mount } from '@vue/test-utils'
import Dashboard from '@/pages/Dashboard.vue'
import { createStore } from 'vuex'
import router from '@/router'

describe('Dashboard.vue', () => {
  test('displays user ID from props', async () => {
    const wrapper = mount(Dashboard, {
      props: {
        userId: '100'
      },
      global: {
        plugins: [router]
      }
    })
    expect(wrapper.text()).toContain('Welcome to Your Dashboard')
    // The console.log in mounted won't appear in the test, but we can confirm that
    // the prop is recognized:
    expect(wrapper.vm.userId).toBe('100')
  })

  test('logoutUser dispatches logout and navigates to /login', async () => {
    const actions = {
      logout: jest.fn()
    }
    const store = createStore({
      actions
    })

    const wrapper = mount(Dashboard, {
      props: {
        userId: '100'
      },
      global: {
        plugins: [store, router]
      }
    })

    const logoutBtn = wrapper.find('button.logout-btn')
    await logoutBtn.trigger('click')

    expect(actions.logout).toHaveBeenCalled()
    expect(wrapper.vm.$router.currentRoute.value.path).toBe('/login')
  })
})