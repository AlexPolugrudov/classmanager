package com.polugrudov.classmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.polugrudov.classmanager.entity.Class;
import com.polugrudov.classmanager.entity.Subject;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    @NonNull
    private int id;

    @NonNull
    @JsonProperty("subject_name")
    private Subject subjectName;

    @NonNull
    private List<Class> classes;
}
