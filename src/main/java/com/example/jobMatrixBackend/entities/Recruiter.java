package com.example.jobMatrixBackend.entities;



import java.util.ArrayList;

import java.util.List;




import jakarta.persistence.*;

@Entity
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String representativeName;
    private String email;
    private String password;

    private String companyLogoUrl;
    private String companyDescription;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Jobbb> jobsPosted = new ArrayList<>();

    // Getters and Setters
    public Recruiter() {
    }

    public Recruiter(String companyName, String representativeName, String email, String password,
            String companyLogoUrl, String companyDescription, List<Jobbb> jobsPosted) {
        this.companyName = companyName;
        this.representativeName = representativeName;
        this.email = email;
        this.password = password;
        this.companyLogoUrl = companyLogoUrl;
        this.companyDescription = companyDescription;
        this.jobsPosted = jobsPosted;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public List<Jobbb> getJobsPosted() {
        return jobsPosted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public void setJobsPosted(List<Jobbb> jobsPosted) {
        this.jobsPosted = jobsPosted;
    }

    

    
}
