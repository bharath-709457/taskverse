package com.taskverse.taskverse.repository;

import com.taskverse.taskverse.model.Project;
import com.taskverse.taskverse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(User owner);
}
