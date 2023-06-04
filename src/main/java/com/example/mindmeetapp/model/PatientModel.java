package com.example.mindmeetapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PatientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="userid")
    private UserModel userModel;
    @Column(name="dateOfBirth")
    private Date dateOfBirth;
    @Column(name="pesel")
    private Long pesel;
    @Column(name="disease")
    private String disease;

/*INSERT INTO patient_model (userid, date_of_birth, pesel, disease)
VALUES (1, '1990-01-01', 1234567890, 'Some disease');*/
}
