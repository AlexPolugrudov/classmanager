package com.polugrudov.classmanager.factory;

import com.polugrudov.classmanager.dto.ClassnameDto;
import com.polugrudov.classmanager.entity.Classname;
import org.springframework.stereotype.Component;

@Component
public class ClassnameDtoFactory {

    public ClassnameDto makeClassnameDto(Classname classname) {
        return ClassnameDto.builder()
                .id(classname.getId())
                .digit(classname.getDigit())
                .letter(classname.getLetter())
                .build();
    }
}
