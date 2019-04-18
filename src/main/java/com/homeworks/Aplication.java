package com.homeworks;

import com.homeworks.Implementation.DeveloperDaoImplementation;
import com.homeworks.Util.HibernateUtil;
import com.homeworks.entity.Developer;

import javax.persistence.EntityManager;

public class Aplication {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

        Developer developer = new Developer();
        developer.setName("Alex");
        developer.setAge(20);
        developer.setSalary(3000);

        DeveloperDaoImplementation developerDao = new DeveloperDaoImplementation(entityManager);
        developerDao.insertDeveloper(developer);
        HibernateUtil.shutdown();
    }
}
