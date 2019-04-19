package com.homeworks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Log4j
@Data
@Entity
@Table(name = "developers")
public class Developer extends Model {
    @Column(name = "age")
    private Integer age;

    @Column(name = "salary")
    private Integer salary = 0;

    @ManyToMany
    @JoinTable(name = "developer_skill",
            joinColumns = {@JoinColumn(name = "developer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")})
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "developers")
    @JoinTable(name = "developer_project", joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projects = new HashSet<>();

    @PrePersist
    public void prePersist() {
        log.info("Project.onPrePersist()");
    }

    @PostPersist
    public void postPersist() {
        log.info("Project.onPostPersist()");
    }

    @PreRemove
    public void preRemove() {
        log.info("Project.onPreRemove()");
    }

    @PostRemove
    public void postRemove() {
        log.info("Project.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Project.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Project.onPostUpdate()");
    }

    @PostLoad
    public void postLoad() {
        log.info("Project.onPostLoad()");
    }
}
