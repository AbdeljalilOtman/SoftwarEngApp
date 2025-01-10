import { mount } from '@vue/test-utils'
import Account from '@/pages/Account.vue'
import axios from 'axios'
import flushPromises from 'flush-promises'
import router from '@/router'

// Mock the axios module
jest.mock('axios')

describe('Account.vue', () => {
  const mockUserData = {
    name: 'John Doe',
    email: 'john@example.com',
    fieldOfInterest: 'Engineering',
    institutionName: 'MIT',
    programName: 'Computer Science',
    resumeUrl: 'http://example.com/resume.pdf',
    portfolioUrl: 'http://example.com/portfolio'
  }

  beforeEach(() => {
    jest.resetAllMocks()
  })

  test('loads user data on created', async () => {
    axios.get.mockResolvedValue({ data: mockUserData })

    const wrapper = mount(Account, {
      global: {
        plugins: [router]
      },
      props: {
        // Using the route param as if /account/1
        // Simulate that in the test by mocking $route.params
      },
      mocks: {
        $route: {
          params: { userId: '1' }
        }
      }
    })

    await flushPromises()
    expect(axios.get).toHaveBeenCalledWith(
      'http://localhost:8080/api/job-seekers/1'
    )
    expect(wrapper.vm.name).toBe('John Doe')
    expect(wrapper.vm.email).toBe('john@example.com')
    expect(wrapper.find('input[type="email"]').element.value).toBe('john@example.com')
  })

  test('updateAccount sends PUT request', async () => {
    axios.get.mockResolvedValue({ data: mockUserData })
    axios.put.mockResolvedValue({ data: { success: true } })

    const wrapper = mount(Account, {
      global: {
        plugins: [router]
      },
      mocks: {
        $route: {
          params: { userId: '1' }
        }
      }
    })

    await flushPromises()

    // Change a field
    await wrapper.find('input[type="text"]').setValue('New Name')
    await wrapper.find('form').trigger('submit.prevent')
    await flushPromises()

    expect(axios.put).toHaveBeenCalledWith(
      'http://localhost:8080/api/job-seekers/1',
      expect.objectContaining({
        name: 'New Name'
      })
    )
  })
})