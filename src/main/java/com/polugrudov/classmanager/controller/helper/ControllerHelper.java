package com.polugrudov.classmanager.controller.helper;

import com.polugrudov.classmanager.entity.Student;
import com.polugrudov.classmanager.entity.Teacher;
import com.polugrudov.classmanager.exception.NotFoundException;
import com.polugrudov.classmanager.repository.StudentRepository;
import com.polugrudov.classmanager.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ControllerHelper {

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    public Teacher getTeacherOrThrowException(int teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Teacher with \"%s\" doesn't exist", teacherId)));
    }

    public Student getStudentOrThrowException(int studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Student with \"%s\" doesn't exist", studentId)
                ));
    }
}