package com.taskverse.taskverse.controller;

import com.taskverse.taskverse.dto.ProjectRequest;
import com.taskverse.taskverse.model.Project;
import com.taskverse.taskverse.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        Project project = projectService.createProject(request, userEmail);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(projectService.getUserProjects(userEmail));
    }
}
