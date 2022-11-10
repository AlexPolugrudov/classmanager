package com.polugrudov.classmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String homework;

    @OneToMany
    @JoinColumn(name = "subject_id")
    private List<Teacher> teachers;

    @ManyToMany(mappedBy = "subjects")
    private List<Class> aClass;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

    //TODO: костыль, потом надо изменить так, чтобы оценки сохранялись
    @Transient
    private List<Integer> rates = new ArrayList<>();

}
