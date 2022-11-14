package com.polugrudov.classmanager.repository;

import com.polugrudov.classmanager.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
