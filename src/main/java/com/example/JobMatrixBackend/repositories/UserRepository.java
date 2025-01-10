package com.example.JobMatrixBackend.repositories;


import java.util.Optional;

import com.example.JobMatrixBackend.entities.JobSeeker;
import com.example.JobMatrixBackend.entities.Recruiter;
import com.example.JobMatrixBackend.entities.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
