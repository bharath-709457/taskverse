package com.taskverse.taskverse.service.impl;

import com.taskverse.taskverse.dto.ProjectRequest;
import com.taskverse.taskverse.model.Project;
import com.taskverse.taskverse.model.User;
import com.taskverse.taskverse.repository.ProjectRepository;
import com.taskverse.taskverse.repository.UserRepository;
import com.taskverse.taskverse.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Project createProject(ProjectRequest request, String userEmail) {
        User owner = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setOwner(owner);

        return projectRepository.save(project);
    }

    @Override
    public List<Project> getUserProjects(String userEmail) {
        User owner = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return projectRepository.findByOwner(owner);
    }
}
