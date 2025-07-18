package com.taskverse.taskverse.service.impl;

import com.taskverse.taskverse.model.Task;
import com.taskverse.taskverse.model.Project;
import com.taskverse.taskverse.model.User;
import com.taskverse.taskverse.repository.TaskRepository;
import com.taskverse.taskverse.repository.UserRepository;
import com.taskverse.taskverse.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Task createTask(Project project, String title, String description) {
        Task task = Task.builder()
                .title(title)
                .description(description)
                .completed(false)
                .createdAt(LocalDateTime.now())
                .project(project)
                .build();
        return taskRepository.save(task);
    }

    @Override
    public Task saveTaskForUser(Task task, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByProject(Project project) {
        return taskRepository.findByProject(project);
    }

    @Override
    public Task markTaskAsCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}
