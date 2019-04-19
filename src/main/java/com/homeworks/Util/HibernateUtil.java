package com.homeworks.Util;

import com.homeworks.entity.Model;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class HibernateUtil {
    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory entityManagerFactory;

    private HibernateUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable e) {
            Model.log.info("Initial SessionFactory creation failed." + e);
        }
        return entityManagerFactory;
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
