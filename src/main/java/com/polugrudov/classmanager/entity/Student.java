package com.polugrudov.classmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private PersonalInformation personalInformation;

    @ManyToOne
    private Classname aClass;

    @ManyToMany
    @JoinColumn(name = "student_id")
    private List<Subject> subjects;

}
