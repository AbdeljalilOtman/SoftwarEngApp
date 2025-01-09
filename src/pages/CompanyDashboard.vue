<template>
    <div class="company-dashboard">
      <!-- Welcome Header -->
      <div class="dashboard-header">
        <h1>Welcome, {{ companyName }}</h1>
        <p>Manage job posts, view applicants, and update your company profile.</p>
      </div>
  
      <!-- Navigation Actions -->
      <div class="actions">
        <router-link to="/create-job-post" class="btn-primary">
          Create a New Job Post
        </router-link>
        <router-link to="/company-account" class="btn-secondary">
          Company Account Settings
        </router-link>
      </div>
  
      <!-- Job Posts Section -->
      <div class="job-posts-section">
        <h2>My Job Posts</h2>
        <div v-if="jobs && jobs.length">
          <ul class="job-posts-list">
            <li v-for="job in jobs" :key="job.id" class="job-item">
              <div class="job-info">
                <h3>{{ job.title }}</h3>
                <p>Status: <strong>{{ job.status }}</strong></p>
              </div>
              <button class="btn-small" @click="viewApplicants(job.id)">
                View Applicants
              </button>
            </li>
          </ul>
        </div>
        <div v-else class="no-jobs">
          <p>No job posts available.</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import api from "@/services/api.js";
  
  export default {
  name: "CompanyDashboard",
  data() {
    return {
      companyName: "",
      jobs: [],
    };
  },
  async mounted() {
    const recruiterId = this.$route.params.id; // Get the recruiter ID from the path parameter
    try {
      const me = await api.get(`/api/recruiters/${recruiterId}`);
      this.companyName = me.data.companyName || "Company";
    } catch (error) {
      console.error("Error fetching recruiter info:", error);
    }
    this.listMyJobs(recruiterId);
  },
  methods: {
    async listMyJobs(recruiterId) {
      try {
        const res = await api.get(`/api/recruiters/${recruiterId}/my-jobs`);
        this.jobs = res.data || [];
      } catch (error) {
        console.error("Failed to fetch jobs:", error);
      }
    },
    viewApplicants(jobId) {
      alert(`View applicants for Job ID: ${jobId}`);
    },
  },
};

  </script>
  
  <style scoped>
  /* General Styling */
  .company-dashboard {
    font-family: Arial, sans-serif;
    padding: 2rem;
    background-color: #f9f9f9;
    min-height: 100vh;
  }
  
  /* Header Section */
  .dashboard-header {
    text-align: center;
    margin-bottom: 2rem;
  }
  .dashboard-header h1 {
    font-size: 2rem;
    color: #333;
  }
  .dashboard-header p {
    color: #666;
  }
  
  /* Navigation Actions */
  .actions {
    display: flex;
    justify-content: center;
    gap: 1.5rem;
    margin-bottom: 2rem;
  }
  .btn-primary {
    background-color: #4caf50;
    color: #fff;
    padding: 0.8rem 1.5rem;
    border: none;
    border-radius: 4px;
    text-decoration: none;
    font-weight: bold;
    transition: background-color 0.2s;
  }
  .btn-primary:hover {
    background-color: #43a047;
  }
  
  .btn-secondary {
    background-color: #007bff;
    color: #fff;
    padding: 0.8rem 1.5rem;
    border: none;
    border-radius: 4px;
    text-decoration: none;
    font-weight: bold;
    transition: background-color 0.2s;
  }
  .btn-secondary:hover {
    background-color: #0056b3;
  }
  
  /* Job Posts Section */
  .job-posts-section {
    background-color: #fff;
    padding: 1.5rem;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  .job-posts-section h2 {
    margin-bottom: 1rem;
    color: #333;
  }
  
  /* Job Posts List */
  .job-posts-list {
    list-style: none;
    padding: 0;
  }
  .job-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
    margin-bottom: 1rem;
    background-color: #f7f7f7;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  .job-info h3 {
    margin: 0;
    color: #333;
  }
  .job-info p {
    margin: 0.3rem 0 0;
    color: #666;
  }
  .btn-small {
    background-color: #007bff;
    color: #fff;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 4px;
    font-size: 0.9rem;
    cursor: pointer;
    transition: background-color 0.2s;
  }
  .btn-small:hover {
    background-color: #0056b3;
  }
  
  /* No Jobs Placeholder */
  .no-jobs {
    text-align: center;
    color: #777;
    font-style: italic;
  }
  </style>
  