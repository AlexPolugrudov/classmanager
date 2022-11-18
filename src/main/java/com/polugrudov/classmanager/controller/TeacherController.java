package com.polugrudov.classmanager.controller;

import com.polugrudov.classmanager.dto.AskDto;
import com.polugrudov.classmanager.dto.TeacherDto;
import com.polugrudov.classmanager.entity.Classname;
import com.polugrudov.classmanager.entity.Subject;
import com.polugrudov.classmanager.entity.Teacher;
import com.polugrudov.classmanager.exception.BadRequestException;
import com.polugrudov.classmanager.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    private static final String FETCH_TEACHER = "/all/teachers/{teacher_id}";
    private static final String CREATE_TEACHER = "all/teachers/new";
    private static final String EDIT_TEACHER = "/all/teachers/{teacher_id}";
    private static final String DELETE_TEACHER = "/all/teachers/{teacher_id}";

    @GetMapping(FETCH_TEACHER)
    public TeacherDto fetchTeacher(@PathVariable("teacher_id") int teacherId) {
        return teacherService.findTeacher(teacherId);
    }

    @PutMapping(CREATE_TEACHER)
    public TeacherDto createTeacher(@RequestParam("fio") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("telephone_number") String telephoneNumber,
                                    @RequestParam("subjectName")Subject subject) {

        List<Teacher> teachers = teacherService.findAll();

        for (Teacher teacher : teachers) {
            if (teacher.getPersonalInformation().getFio().equals(name))
                if (teacher.getPersonalInformation().getEmail().equals(email))
                    throw new BadRequestException(
                            String.format("Teacher with \"%s\" name and \"%s\" email already exist", name, email)
                    );
        }

        TeacherDto newTeacher = new TeacherDto();

        newTeacher.getPersonalInformation().setFio(name);
        newTeacher.getPersonalInformation().setEmail(email);
        newTeacher.getPersonalInformation().setTelephoneNumber(telephoneNumber);
        newTeacher.setSubjectName(subject);

        return teacherService.createTeacher(newTeacher);
    }

    @PutMapping(EDIT_TEACHER)
    public TeacherDto editTeacher(@PathVariable("teacher_id") int teacherId,
                                  @RequestParam(value = "subject", required = false) Optional<Subject> updatedSubject,
                                  @RequestParam(value = "class", required = false) Optional<Classname> updatedClass) {
        return teacherService.editTeacher(teacherId, updatedSubject, updatedClass);
    }

    @DeleteMapping(DELETE_TEACHER)
    public AskDto deleteTeacher(@PathVariable("teacher_id") int teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }
}
