package com.polugrudov.classmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.polugrudov.classmanager.entity.Class;
import com.polugrudov.classmanager.entity.People;
import com.polugrudov.classmanager.entity.Subject;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    @NonNull
    private int id;

    @NonNull
    private People people;

    @NonNull
    @JsonProperty("class_name")
    private Class aClass;

    @NonNull
    private List<Subject> subjects;
}
