package com.polugrudov.classmanager.repository;

import com.polugrudov.classmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
