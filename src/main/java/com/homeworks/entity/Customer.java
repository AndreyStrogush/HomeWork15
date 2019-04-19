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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "customers")
public class Customer extends Model{
    @Column(name = "country")
    private String country;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customers")
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
