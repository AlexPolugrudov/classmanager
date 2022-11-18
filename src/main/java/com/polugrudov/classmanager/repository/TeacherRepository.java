package com.polugrudov.classmanager.repository;

import com.polugrudov.classmanager.entity.Classname;
import com.polugrudov.classmanager.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    public Classname findClassById(int id);
}
