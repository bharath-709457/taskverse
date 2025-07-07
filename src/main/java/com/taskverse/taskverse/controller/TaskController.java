package com.taskverse.taskverse.controller;

import com.taskverse.taskverse.dto.CreateTaskRequest;
import com.taskverse.taskverse.model.Project;
import com.taskverse.taskverse.model.Task;
import com.taskverse.taskverse.repository.ProjectRepository;
import com.taskverse.taskverse.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ProjectRepository projectRepository;

    @PostMapping("/project/{projectId}")
    public ResponseEntity<Task> createTask(@PathVariable Long projectId, @RequestBody CreateTaskRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        Task task = taskService.createTask(project, request.getTitle(), request.getDescription());
        return ResponseEntity.ok(task);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        return ResponseEntity.ok(taskService.getTasksByProject(project));
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.markTaskAsCompleted(taskId));
    }
}
