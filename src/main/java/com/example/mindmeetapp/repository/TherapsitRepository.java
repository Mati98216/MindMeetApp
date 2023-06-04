package com.example.mindmeetapp.repository;

import com.example.mindmeetapp.model.TherapistModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapsitRepository extends JpaRepository<TherapistModel, Long> {
    Page<TherapistModel> findAll(Pageable pageable);
}
