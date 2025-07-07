package com.taskverse.taskverse.repository;

import com.taskverse.taskverse.model.Task;
import com.taskverse.taskverse.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
}
