package com.polugrudov.classmanager.repository;

import com.polugrudov.classmanager.entity.Class;
import com.polugrudov.classmanager.entity.Student;
import com.polugrudov.classmanager.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    public Class findClassById(int id);
}
