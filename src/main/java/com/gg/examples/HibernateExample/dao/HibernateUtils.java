package com.gg.examples.HibernateExample.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * User: adurmaz
 * Date: 5/26/13
 * Time: 11:21 AM
 */
public class HibernateUtils {

    private  static SessionFactory sessionFactory;
    static {
        //AnnotationConfiguration cfg = new AnnotationConfiguration();
        Configuration cfg = new Configuration();
        cfg.setInterceptor(new AuiditLogInterceptor());
        cfg.configure();
       // sessionFactory = cfg.buildSessionFactory();
        sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry());
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
