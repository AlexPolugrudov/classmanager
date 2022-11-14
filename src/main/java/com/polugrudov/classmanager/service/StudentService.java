package com.polugrudov.classmanager.service;

import com.polugrudov.classmanager.controller.helper.ControllerHelper;
import com.polugrudov.classmanager.dto.AskDto;
import com.polugrudov.classmanager.dto.StudentDto;
import com.polugrudov.classmanager.entity.Class;
import com.polugrudov.classmanager.entity.Student;
import com.polugrudov.classmanager.entity.Subject;
import com.polugrudov.classmanager.factory.StudentDtoFactory;
import com.polugrudov.classmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentDtoFactory studentDtoFactory;

    private final ControllerHelper controllerHelper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentDtoFactory studentDtoFactory, ControllerHelper controllerHelper) {
        this.studentRepository = studentRepository;
        this.studentDtoFactory = studentDtoFactory;
        this.controllerHelper = controllerHelper;
    }

    public StudentDto findStudent(int studentId) {

        return studentDtoFactory.makeStudentDto(
                controllerHelper.getStudentOrThrowException(studentId)
        );
    }

    @Transactional
    public StudentDto createStudent(StudentDto studentDto) {
        Student newStudent = new Student();

        newStudent.setId(studentDto.getId());
        newStudent.setPeople(studentDto.getPeople());
        newStudent.setAClass(studentDto.getAClass());
        newStudent.setSubjects(studentDto.getSubjects());

        final Student savedStudent = studentRepository.saveAndFlush(newStudent);

        return studentDtoFactory.makeStudentDto(savedStudent);
    }

    //TODO: Добавить изменение ПИ в соответствующий сервис(Персональ. информация)
    //Изменение НЕ персональной информации
    @Transactional
    public StudentDto editStudent(int studentId, Optional<Subject> optionalSubject, Optional<Class> optionalClass) {

        Student student = controllerHelper.getStudentOrThrowException(studentId);


        //TODO: сейчас - добавление нового предмета в конец списка
        // добавить: удаление/замену
        if (optionalSubject.isPresent()) {
            List<Subject> oldSubjectList = student.getSubjects();

            oldSubjectList.add(optionalSubject.get());
        }

        if (optionalClass.isPresent()) {
            student.setAClass(optionalClass.get());
        }

        studentRepository.saveAndFlush(student);

        //Заглушка
        return studentDtoFactory.makeStudentDto(student);
    }

    @Transactional
    public AskDto deleteStudent(int studentId) {
        controllerHelper.getStudentOrThrowException(studentId);

        studentRepository.deleteById(studentId);

        return AskDto.makeDefault(true);
    }
}
