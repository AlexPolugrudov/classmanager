package com.polugrudov.classmanager.factory;

import com.polugrudov.classmanager.dto.TeacherDto;
import com.polugrudov.classmanager.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherDtoFactory {

    public TeacherDto makeTeacherDto(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .subjectName(teacher.getSubjectName())
                .classes(teacher.getClasses())
                .build();
    }
}
