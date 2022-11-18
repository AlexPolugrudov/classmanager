package com.polugrudov.classmanager.controller;

import com.polugrudov.classmanager.dto.StudentDto;
import com.polugrudov.classmanager.entity.Student;
import com.polugrudov.classmanager.exception.BadRequestException;
import com.polugrudov.classmanager.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private static final String FETCH_STUDENT = "/all/students/{student_id}";
    private static final String CREATE_STUDENT = "/all/students/new";

    @GetMapping(FETCH_STUDENT)
    public StudentDto fetchStudent(@PathVariable("student_id") int studentId) {
        return studentService.findStudent(studentId);
    }

    @PutMapping(CREATE_STUDENT)
    public StudentDto createStudent(@RequestParam(value = "fio") String fullName,
                                    @RequestParam(value = "email") String email,
                                    @RequestParam(value = "telephone_number")String telephoneNumber,
                                    @RequestParam(value = "class") String studentClass) {

        List<Student> students = studentService.findAll();

        for (Student student : students)
            if (student.getPersonalInformation().getFio().equals(fullName))
                if (student.getPersonalInformation().getEmail().equals(email))
                    throw new BadRequestException(
                            String.format("Student with \"%s\" name and \"%s\" email already exist", fullName, email)
                    );

        StudentDto studentDto = new StudentDto();

        studentDto.getPersonalInformation().setFio(fullName);
        studentDto.getPersonalInformation().setEmail(email);
        studentDto.getPersonalInformation().setTelephoneNumber(telephoneNumber);
//        studentDto.setAClass(studentClass);

        //TODO: убрать заглушку и комментарий сверху
        return null;
    }
}
