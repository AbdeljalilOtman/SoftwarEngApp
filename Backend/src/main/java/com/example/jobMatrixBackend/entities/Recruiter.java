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

    // public Recruiter(String companyName, String representativeName, String email, String password,
    //         String companyLogoUrl, String companyDescription, List<Jobbb> jobsPosted) {
    //     this.companyName = companyName;
    //     this.representativeName = representativeName;
    //     this.email = email;
    //     this.password = password;
    //     this.companyLogoUrl = companyLogoUrl;
    //     this.companyDescription = companyDescription;
    //     this.jobsPosted = jobsPosted;
    // }

    
}
