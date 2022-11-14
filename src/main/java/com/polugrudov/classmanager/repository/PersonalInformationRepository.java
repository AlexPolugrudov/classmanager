package com.polugrudov.classmanager.repository;

import com.polugrudov.classmanager.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Integer> {
}
