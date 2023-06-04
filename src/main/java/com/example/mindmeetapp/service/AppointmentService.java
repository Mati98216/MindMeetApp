package com.example.mindmeetapp.service;

import com.example.mindmeetapp.model.AppointmentModel;
import com.example.mindmeetapp.model.PatientModel;
import com.example.mindmeetapp.repository.AppointmentRepository;
import com.example.mindmeetapp.util.AppointmentException;
import com.example.mindmeetapp.util.AppointmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Service
@RequiredArgsConstructor
@Validated
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;



    public void createAppointment(@Valid AppointmentModel appointment) throws AppointmentException {


        try {
            appointmentRepository.save(appointment);

        } catch (Exception e) {

            throw new AppointmentException("Cannot create new appointment", e);
        }
    }


    public void bookAppointment(@Valid AppointmentModel appointment, @NotNull PatientModel patient) throws AppointmentException {
        try {
            // Sprawdz czy wizyta jest dostępna
            List<AppointmentModel> appointments = appointmentRepository.findByStartDateBetween(appointment.getStartDate(), appointment.getEndDate());
            for (AppointmentModel apt : appointments) {
                if (apt.getTherapistModelId().equals(appointment.getTherapistModelId())) {
                    throw new AppointmentNotFoundException("Therapist is busy");
                }
            }

            // Przypisanie pacjenta do wizyty
            appointment.setPatientModelId(patient);
            appointmentRepository.save(appointment);

        } catch (Exception e) {
            throw new AppointmentException("Cannot book appointment", e);
        }
    }

    public List<AppointmentModel> getAppointments() {
        return appointmentRepository.findAll();
    }

    public void editAppointment( @Valid AppointmentModel appointment) throws AppointmentException {

        try {
            appointmentRepository.save(appointment);

        } catch (Exception e) {
            throw new AppointmentException("Cannot edit appointment", e);
        }
    }

    public AppointmentModel getAppointmentById( Integer id) throws AppointmentNotFoundException {
        Optional<AppointmentModel> optionalAppointmentModel = appointmentRepository.findById(id);
        if (optionalAppointmentModel.isPresent()) {
            return optionalAppointmentModel.get();
        } else {
            throw new AppointmentNotFoundException("Appointment not found");
        }


    }
    public List<AppointmentModel> getUpcomingAppointmentsByPatientId(Integer patientId) {
        return appointmentRepository.findUpcomingByPatientModelId_Id(patientId);
    }

    public List<AppointmentModel> getUpcomingAppointmentsByTherapistId(Integer therapistId) {
        return appointmentRepository.findUpcomingByTherapistModelId_Id(therapistId);
    }
}




