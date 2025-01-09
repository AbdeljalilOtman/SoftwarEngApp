<template>
    <div class="create-job-post container-small">
      <div class="header-row">
        <h2>Create a New Job Post</h2>
        <router-link to="/company-dashboard" class="btn-back">Back to Dashboard</router-link>
      </div>
      <form @submit.prevent="submitJob">
        <div>
          <label>Title:</label>
          <input v-model="title" type="text" required />
        </div>
  
        <div>
          <label>Description:</label>
          <textarea v-model="description" rows="3"></textarea>
        </div>
  
        <div>
          <label>Experience Required (years):</label>
          <input v-model.number="experienceRequired" type="number" min="0" required />
        </div>
  
        <div>
          <label>Location:</label>
          <input v-model="location" type="text" />
        </div>
  
        <div>
          <label>Job Type:</label>
          <select v-model="jobType">
            <option value="">-- Select --</option>
            <option value="Full-Time">Full-Time</option>
            <option value="Part-Time">Part-Time</option>
            <option value="Internship">Internship</option>
          </select>
        </div>
  
        <div>
          <label>Application Deadline:</label>
          <input v-model="applicationDeadline" type="date" />
        </div>
  
        <div>
          <label>Status:</label>
          <select v-model="status">
            <option value="">-- Select --</option>
            <option value="Open">Open</option>
            <option value="Closed">Closed</option>
          </select>
        </div>
  
        <button type="submit">Create Job</button>
      </form>
    </div>
  </template>
  
  <script>
  import api from "@/services/api.js";
  
  export default {
    name: "CreateJobPost",
    data() {
      return {
        title: "",
        description: "",
        experienceRequired: 0,
        location: "",
        jobType: "",
        applicationDeadline: "",
        status: "",
      };
    },
    methods: {
      async submitJob() {
        try {
          const response = await api.post("/api/jobs", {
            title: this.title,
            description: this.description,
            experienceRequired: this.experienceRequired,
            location: this.location,
            jobType: this.jobType,
            applicationDeadline: this.applicationDeadline || null,
            status: this.status || "Open",
          });
          console.log("Job created:", response.data);
          alert("Job post created successfully!");
          this.$router.push("/company-dashboard");
        } catch (error) {
          console.error("Failed to create job post:", error.response || error);
          alert("Error creating job post. Check console for details.");
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .create-job-post {
    margin-top: 2rem;
  }
  .header-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 1rem;
  }
  .btn-back {
    background-color: #4caf50;
    color: #fff !important;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    text-decoration: none;
    transition: background-color 0.2s;
  }
  .btn-back:hover {
    background-color: #43a047;
  }
  .create-job-post form > div {
    margin-bottom: 1rem;
  }
  </style>
  