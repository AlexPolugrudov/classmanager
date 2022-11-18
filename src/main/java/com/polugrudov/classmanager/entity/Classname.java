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
@Table(name = "class")
public class Classname {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int digit;

    private Character letter;

    @ManyToMany(mappedBy = "classes")
    private List<Teacher> teachers;

    @OneToMany
    @JoinColumn(name = "class_id")
    private List<Student> students;

    @ManyToMany
    private List<Subject> subjects;
}
