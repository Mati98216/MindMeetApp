package com.example.mindmeetapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "enddate")
    private Date endDate;

    @Column(name = "place")
    private String place;

    @ManyToOne
    @JoinColumn(name = "patientid")
    private PatientModel patientModelId;

    @ManyToOne
    @JoinColumn(name = "therapistid")
    private TherapistModel therapistModelId;

    @Column(name = "description")
    private String description;

}