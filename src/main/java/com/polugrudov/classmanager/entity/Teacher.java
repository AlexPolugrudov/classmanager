package com.polugrudov.classmanager.entity;

import com.polugrudov.classmanager.exception.BadRequestException;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private PersonalInformation personalInformation;

    @ManyToOne
    private Subject subjectName;

    @ManyToMany
    private List<Classname> classes;


    public void updateHomework(String updatedHomework) {

        if (updatedHomework.isEmpty())
            throw new BadRequestException("Домашнее задание не должно быть пустым");

        //TODO: изменить метод и добавить возможность добавления ДЗ
        if (subjectName.getHomework().isEmpty()) {
            subjectName.setHomework(updatedHomework);
        }
    }
}
