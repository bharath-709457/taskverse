package com.taskverse.taskverse.service;

import com.taskverse.taskverse.model.Task;
import com.taskverse.taskverse.model.Project;

import java.util.List;

public interface TaskService {
    Task createTask(Project project, String title, String description);
    List<Task> getTasksByProject(Project project);
    Task markTaskAsCompleted(Long taskId);
    Task saveTaskForUser(Task task, String userEmail);
    List<Task> getAllTasks();
}
