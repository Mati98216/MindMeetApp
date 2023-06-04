package com.example.mindmeetapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private  String username;
    @Column(name="password")
    private String password;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="phoneNumber") 
    private Long phoneNumber;
    @Column(name="email")
    private String email;
    @Column(name="isAdministrator")
    private Boolean isAdministrator;

}
