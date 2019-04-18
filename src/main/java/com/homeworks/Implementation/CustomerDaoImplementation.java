package com.homeworks.Implementation;

import com.homeworks.dao.CustomerDao;
import com.homeworks.entity.Customer;
import com.homeworks.entity.Model;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerDaoImplementation implements CustomerDao {
    private EntityManager entityManager;

    public CustomerDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insertCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            Model.log.error("RuntimeException: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            Model.log.error("RuntimeException: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Customer readCustomer(Long id) {
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
            return customer;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            Model.log.error("RuntimeException: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteCustomer(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            Model.log.error("RuntimeException: " + e.getMessage());
            return false;
        }
    }
}
