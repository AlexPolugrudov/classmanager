package com.polugrudov.classmanager.dto;

import com.polugrudov.classmanager.entity.Class;
import com.polugrudov.classmanager.entity.Teacher;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private int id;

    private String name;

    private String homework;

    private List<Teacher> teachers;

    private List<Class> classes;

}
