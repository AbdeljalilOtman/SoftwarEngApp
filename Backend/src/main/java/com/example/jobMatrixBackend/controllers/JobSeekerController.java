package com.example.jobMatrixBackend.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jobMatrixBackend.*;
import com.example.jobMatrixBackend.models.JobSeekerModel;


@Controller
@RequestMapping("/api/JobSeekers")
public class JobSeekerController {

    @Autowired
    private JobSeekerModel Jobseeker_model;

    // Display login form
    @RequestMapping("/all")
    public String showLoginForm() {
        return "login";  // This will load login.html from the templates folder
    }
    // Display dashboard page
    @RequestMapping("/dashboard")
    public String showDashboard(@ModelAttribute("JobSeeker") JobSeeker JobSeeker, Model model) {
        if (JobSeeker == null) {
            return "redirect:/api/JobSeekers/login"; // Redirect if JobSeeker is missing
        }
        model.addAttribute("JobSeeker", JobSeeker);
        return "dashboard";
    }

    // Add logout functionality (optional)
    @RequestMapping("/logout")
    public String logout() {
        // Perform logout actions here if needed (e.g., clearing session)
        return "redirect:/api/JobSeekers/login";  // Redirect back to the login page after logout
    }
}

