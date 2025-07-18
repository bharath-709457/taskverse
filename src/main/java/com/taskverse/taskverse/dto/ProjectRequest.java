package com.taskverse.taskverse.dto;

import lombok.Data;

@Data
public class ProjectRequest {
    private String name;
    private String description;

    public ProjectRequest() {}

    public ProjectRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
