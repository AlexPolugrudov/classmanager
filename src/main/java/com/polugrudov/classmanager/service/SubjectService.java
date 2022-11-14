package com.polugrudov.classmanager.service;

import com.polugrudov.classmanager.controller.helper.ControllerHelper;
import com.polugrudov.classmanager.dto.SubjectDto;
import com.polugrudov.classmanager.entity.Subject;
import com.polugrudov.classmanager.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final ControllerHelper controllerHelper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, ControllerHelper controllerHelper) {
        this.subjectRepository = subjectRepository;
        this.controllerHelper = controllerHelper;
    }

    public Subject findSubject(int subjectId) {
        return controllerHelper.getSubjectOrThrowException(subjectId);
    }

    //TODO: добавить создание предмета
    @Transactional
    public Subject createSubject(SubjectDto subjectDto) {


        List<Subject> subjects = subjectRepository.findAll();

//        for (Subject subject : subjects) {
//            if ()
//        }

        //Заглушка
        return null;
    }
}
