package com.example.jobMatrixBackend.repositories;



import java.util.Optional;

import com.example.jobMatrixBackend.entities.JobSeeker;
import com.example.jobMatrixBackend.entities.Recruiter;
import com.example.jobMatrixBackend.entities.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}