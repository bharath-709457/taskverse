package com.taskverse.taskverse.service;

import com.taskverse.taskverse.dto.ProjectRequest;
import com.taskverse.taskverse.model.Project;
import java.util.List;

public interface ProjectService {
    Project createProject(ProjectRequest request, String userEmail);
    List<Project> getUserProjects(String userEmail);
}
