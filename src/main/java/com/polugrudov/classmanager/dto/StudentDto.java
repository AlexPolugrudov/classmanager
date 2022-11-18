package com.polugrudov.classmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.polugrudov.classmanager.entity.Classname;
import com.polugrudov.classmanager.entity.PersonalInformation;
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
    private PersonalInformation personalInformation;

    @NonNull
    @JsonProperty("class_name")
    private Classname aClass;

    @NonNull
    private List<Subject> subjects;
}
