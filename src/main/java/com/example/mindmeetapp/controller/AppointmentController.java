package com.example.mindmeetapp.controller;

import com.example.mindmeetapp.model.AppointmentModel;
import com.example.mindmeetapp.model.PatientModel;
import com.example.mindmeetapp.service.AppointmentService;
import com.example.mindmeetapp.util.AppointmentException;
import com.example.mindmeetapp.util.AppointmentNotFoundException;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
//@Api(tags = "Appointments")
public class AppointmentController {

   private final AppointmentService appointmentService;




    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  //  @ApiOperation("Create a new appointment")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentModel appointment) {
       try {
           appointmentService.createAppointment(appointment);
           return new ResponseEntity<>("Appointment created successfully", HttpStatus.CREATED);
       } catch (AppointmentException e) {
          return new ResponseEntity<>("Cannot create new appointment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
       }
   }



    @PutMapping("/{id}")
    public ResponseEntity<String> editAppointment(@PathVariable("id") Integer id, @RequestBody AppointmentModel appointment) {
       appointment.setId(id);
       try {
           appointmentService.editAppointment(appointment);
            return new ResponseEntity<>("Appointment edited successfully", HttpStatus.OK);
       } catch (AppointmentException e) {
            return new ResponseEntity<>("Cannot edit appointment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }


   }


   @GetMapping("/{id}")
 //  @ApiOperation("Get an appointment by ID")
    public ResponseEntity<AppointmentModel> getAppointmentById(@PathVariable("id") Integer id) {
       try {
            AppointmentModel appointment = appointmentService.getAppointmentById(id);
           return new ResponseEntity<>(appointment, HttpStatus.OK);

       } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }


   }


    @GetMapping("/upcoming/patients/{patientId}")
   // @ApiOperation("Get upcoming appointments by patient ID")
   public ResponseEntity<List<AppointmentModel>> getUpcomingAppointmentsByPatientId(@PathVariable("patientId") Integer patientId) {
       List<AppointmentModel> appointments = appointmentService.getUpcomingAppointmentsByPatientId(patientId);
      return new ResponseEntity<>(appointments, HttpStatus.OK);

    }

   @GetMapping("/upcoming/therapist/{therapistId}")
 //  @ApiOperation("Get upcoming appointments by Therapist ID")
    public ResponseEntity<List<AppointmentModel>> getUpcomingAppointmentsByTherapistId(@PathVariable("therapistId") Integer therapistId) {
       List<AppointmentModel> appointments = appointmentService.getUpcomingAppointmentsByTherapistId(therapistId);
       return new ResponseEntity<>(appointments, HttpStatus.OK);
   }

   @PostMapping("/book")
//   @ApiOperation("Book appointment")
   public ResponseEntity<String> bookAppointment(@RequestBody AppointmentModel appointment, @RequestParam("patientId") Long patientId) {

       try {
            PatientModel patient = new PatientModel();
           patient.setId(patientId);
           appointmentService.bookAppointment(appointment, patient);
           return new ResponseEntity<>("Appointment booked successfully", HttpStatus.OK);


        } catch (AppointmentException e) {
            return new ResponseEntity<>("Cannot book appointment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
       }

   }

    @GetMapping
 //   @ApiOperation("Get all appointments")
    public ResponseEntity<List<AppointmentModel>> getAllAppointments() {
        List<AppointmentModel> appointments = appointmentService.getAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
}

}



