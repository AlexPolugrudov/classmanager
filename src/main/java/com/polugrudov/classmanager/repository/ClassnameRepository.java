package com.polugrudov.classmanager.repository;

import com.polugrudov.classmanager.entity.Classname;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassnameRepository extends JpaRepository<Classname, Integer> {
}
