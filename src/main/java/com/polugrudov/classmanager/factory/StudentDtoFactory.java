package com.polugrudov.classmanager.factory;

import com.polugrudov.classmanager.dto.StudentDto;
import com.polugrudov.classmanager.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoFactory {

    public StudentDto makeStudentDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .personalInformation(student.getPersonalInformation())
                .aClass(student.getAClass())
                .subjects(student.getSubjects())
                .build();
    }
}
