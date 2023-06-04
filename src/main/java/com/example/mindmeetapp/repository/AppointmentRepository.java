package com.example.mindmeetapp.repository;

import com.example.mindmeetapp.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Integer> {


    List<AppointmentModel> findByStartDateBetween(Date startDate, Date endDate);


    List<AppointmentModel> findUpcomingByPatientModelId_Id(Integer patientId);


    List<AppointmentModel> findUpcomingByTherapistModelId_Id(Integer therapistId);
}
