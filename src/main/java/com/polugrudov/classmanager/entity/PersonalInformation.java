package com.polugrudov.classmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personal_information")
public class PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "people_id",referencedColumnName = "id", unique = true)
    private People people;

    @Column(name = "name")
    private String fio;

    private String email;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    private String address;

    private String login;

    private String password;
}
