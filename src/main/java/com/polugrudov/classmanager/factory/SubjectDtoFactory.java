package com.polugrudov.classmanager.factory;

import com.polugrudov.classmanager.dto.SubjectDto;
import com.polugrudov.classmanager.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectDtoFactory {

    public SubjectDto makeSubjectDto(Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .teachers(subject.getTeachers())
                .classes(subject.getAClass())
                .homework(subject.getHomework())
                .build();
    }
}
