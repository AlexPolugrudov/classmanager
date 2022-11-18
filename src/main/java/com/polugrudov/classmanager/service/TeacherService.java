package com.polugrudov.classmanager.service;

import com.polugrudov.classmanager.controller.helper.ControllerHelper;
import com.polugrudov.classmanager.dto.AskDto;
import com.polugrudov.classmanager.dto.TeacherDto;
import com.polugrudov.classmanager.entity.Classname;
import com.polugrudov.classmanager.entity.Subject;
import com.polugrudov.classmanager.entity.Teacher;
import com.polugrudov.classmanager.factory.TeacherDtoFactory;
import com.polugrudov.classmanager.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherDtoFactory teacherDtoFactory;

    private final ControllerHelper controllerHelper;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository,
                          TeacherDtoFactory teacherDtoFactory,
                          ControllerHelper controllerHelper) {
        this.teacherRepository = teacherRepository;
        this.teacherDtoFactory = teacherDtoFactory;
        this.controllerHelper = controllerHelper;
    }

    public TeacherDto findTeacher(int teacherId) {
        Teacher teacher = controllerHelper.getTeacherOrThrowException(teacherId);

        return teacherDtoFactory.makeTeacherDto(teacher);
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public TeacherDto saveTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherDtoConvertToTeacherEntity(teacherDto);

        teacherRepository.saveAndFlush(teacher);

        return teacherDtoFactory.makeTeacherDto(teacher);
    }

    //TODO: добавить создание учителя
    @Transactional
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherDtoConvertToTeacherEntity(teacherDto);
        teacherRepository.saveAndFlush(teacher);
        return teacherDtoFactory.makeTeacherDto(teacher);
    }

    //Изменение НЕ персональной информации
    @Transactional
    public TeacherDto editTeacher(int teacherId, Optional<Subject> updatedSubject, Optional<Classname> updatedClass) {
        Teacher teacher = controllerHelper.getTeacherOrThrowException(teacherId);

        if (updatedSubject.isPresent()) {
            Subject subject = updatedSubject.get();

            teacher.setSubjectName(subject);
        }
        if (updatedClass.isPresent()) {
            List<Classname> classes = teacher.getClasses();

            Classname newClass = updatedClass.get();

            classes.add(newClass);
        }

        teacherRepository.saveAndFlush(teacher);

        return teacherDtoFactory.makeTeacherDto(teacher);
    }

    @Transactional
    public AskDto deleteTeacher(int teacherId) {

        controllerHelper.getTeacherOrThrowException(teacherId);

        teacherRepository.deleteById(teacherId);

        return AskDto.makeDefault(true);
    }


    //TODO: скорее всего придется добавить в другой сервис (subject) из-за того что
    // транзакция связана с другой сущностью
    @Transactional
    public TeacherDto createOrUpdateHomework(int teacherId, int classId, String updatedHomework) {

        Teacher teacher = controllerHelper.getTeacherOrThrowException(teacherId);

        teacher.getClasses()
                .get(classId)
                .getSubjects()
                .get(teacher
                        .getSubjectName()
                        .getId())
                .setHomework(updatedHomework);

        teacherRepository.saveAndFlush(teacher);
        return null;
    }

    private Teacher teacherDtoConvertToTeacherEntity(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();

        teacher.setPersonalInformation(teacherDto.getPersonalInformation());
        teacher.setSubjectName(teacherDto.getSubjectName());
        teacher.setClasses(teacherDto.getClasses());

        return teacher;
    }
}