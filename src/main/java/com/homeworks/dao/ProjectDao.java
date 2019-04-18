package com.homeworks.dao;

import com.homeworks.entity.Project;

public interface ProjectDao {
    boolean insertProject(Project project);

    boolean updateProject(Project project);

    Project readProject(Long id);

    boolean deleteProject(Long id);
}
