package com.polugrudov.classmanager.service;

import com.polugrudov.classmanager.controller.helper.ControllerHelper;
import com.polugrudov.classmanager.dto.AskDto;
import com.polugrudov.classmanager.dto.SubjectDto;
import com.polugrudov.classmanager.entity.Subject;
import com.polugrudov.classmanager.exception.BadRequestException;
import com.polugrudov.classmanager.factory.SubjectDtoFactory;
import com.polugrudov.classmanager.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final SubjectDtoFactory subjectDtoFactory;

    private final ControllerHelper controllerHelper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, SubjectDtoFactory subjectDtoFactory, ControllerHelper controllerHelper) {
        this.subjectRepository = subjectRepository;
        this.subjectDtoFactory = subjectDtoFactory;
        this.controllerHelper = controllerHelper;
    }

    public Subject findSubject(int subjectId) {
        return controllerHelper.getSubjectOrThrowException(subjectId);
    }

    @Transactional
    public Subject createSubject(SubjectDto subjectDto) {

        List<Subject> subjects = subjectRepository.findAll();

        for (Subject subject : subjects) {
            if (subject.getName().equals(subjectDto.getName()))
                throw new BadRequestException(
                        String.format("Subject with \"%s\" name already exist.", subjectDto.getName())
                );
        }

        Subject newSubject = new Subject();
        newSubject.setId(subjectDto.getId());
        newSubject.setName(subjectDto.getName());
        newSubject.setAClass(subjectDto.getClasses());
        newSubject.setHomework(subjectDto.getHomework());

        final Subject savedSubject = newSubject;

        subjectRepository.saveAndFlush(savedSubject);

        return savedSubject;
    }

    //TODO: пока что только возможно изменение названия и домашнего названия
    @Transactional
    public Subject editSubject(int subjectId,
                               Optional<String> newSubjectName,
                               Optional<String> newSubjectHomework) {

        Subject updatedSubject = controllerHelper.getSubjectOrThrowException(subjectId);

        if (newSubjectName.isPresent()) {
            updatedSubject.setName(newSubjectName.get());
        }

        if (newSubjectHomework.isPresent()) {
            updatedSubject.setHomework(newSubjectHomework.get());
        }

        final Subject savedSubject = updatedSubject;

        subjectRepository.saveAndFlush(savedSubject);

        return savedSubject;
    }

    @Transactional
    public AskDto deleteSubject(int subjectId) {
        controllerHelper.getSubjectOrThrowException(subjectId);

        subjectRepository.deleteById(subjectId);

        return AskDto.makeDefault(true);
    }
}
