package com.gg.examples.HibernateExample.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * User: adurmaz
 * Date: 5/26/13
 * Time: 11:21 AM
 */
public class HibernateUtils {

    private  static SessionFactory sessionFactory;
    static {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
