package com.example.jobMatrixBackend.repositories;  

import com.example.jobMatrixBackend.entities.Jobbb;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
// If you want to use your real DB, uncomment @AutoConfigureTestDatabase(replace=Replace.NONE)
// and ensure your application-test.properties is set up.

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should save and retrieve a Jobbb entity")
    void testSaveAndFindJob() {
        Jobbb job = new Jobbb();
        job.setTitle("Software Engineer");
        job.setDescription("Java Developer needed");
        job.setStatus("Open");

        jobRepository.save(job);

        List<Jobbb> foundJobs = jobRepository.findAll();
        assertThat(foundJobs).hasSize(1);

        Jobbb savedJob = foundJobs.get(0);
        assertThat(savedJob.getTitle()).isEqualTo("Software Engineer");
        assertThat(savedJob.getDescription()).isEqualTo("Java Developer needed");
        assertThat(savedJob.getStatus()).isEqualTo("Open");
    }
}
