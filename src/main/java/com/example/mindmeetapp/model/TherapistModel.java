package com.example.mindmeetapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TherapistModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name="userid")
    private UserModel userModelId;

    @Column(name="specialization")
    private String specialization;
    /*INSERT INTO therapist_model (userid, specialization)
VALUES (1, 'Specialization');
*/
}
