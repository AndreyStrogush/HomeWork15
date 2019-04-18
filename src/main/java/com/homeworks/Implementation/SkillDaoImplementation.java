package com.homeworks.Implementation;

import com.homeworks.dao.SkillDao;
import com.homeworks.entity.Model;
import com.homeworks.entity.Skill;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Log4j
public class SkillDaoImplementation implements SkillDao {
    private EntityManager entityManager;

    public SkillDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insertSkill(Skill skill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(skill);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            Model.log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(skill);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            Model.log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Skill readSkill(Long id) {
        try {
            entityManager.getTransaction().begin();
            Skill skill = entityManager.find(Skill.class, id);
            entityManager.getTransaction().commit();
            return skill;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            Model.log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteSkill(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Skill.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            Model.log.error(e.getMessage());
            return false;
        }
    }
}
