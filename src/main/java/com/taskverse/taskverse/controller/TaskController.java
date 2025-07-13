package com.taskverse.taskverse.controller;

import com.taskverse.taskverse.dto.CreateTaskRequest;
import com.taskverse.taskverse.model.Project;
import com.taskverse.taskverse.model.Task;
import com.taskverse.taskverse.repository.ProjectRepository;
import com.taskverse.taskverse.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ProjectRepository projectRepository;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, Principal principal) {
        String email = principal.getName();
        Task saved = taskService.saveTaskForUser(task, email); // ✅ use the correct method
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


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

    // ✅ Add this to fix your frontend GET /api/tasks issue
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
