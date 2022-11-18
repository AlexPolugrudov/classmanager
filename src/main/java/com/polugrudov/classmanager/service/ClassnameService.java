package com.polugrudov.classmanager.service;

import com.polugrudov.classmanager.controller.helper.ControllerHelper;
import com.polugrudov.classmanager.dto.ClassnameDto;
import com.polugrudov.classmanager.factory.ClassnameDtoFactory;
import com.polugrudov.classmanager.repository.ClassnameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassnameService {

    private final ClassnameRepository classnameRepository;
    private final ClassnameDtoFactory classnameDtoFactory;

    private final ControllerHelper controllerHelper;

    //TODO: Переделать метод
    public ClassnameDto findClass(int classnameId) {
        return null;
    }
}
