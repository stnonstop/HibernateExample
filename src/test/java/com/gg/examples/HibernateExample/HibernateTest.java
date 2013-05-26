package com.gg.examples.HibernateExample;

import com.gg.examples.HibernateExample.dao.HibernateUtils;
import com.gg.examples.HibernateExample.model.Foo;
import com.gg.examples.HibernateExample.model.Owner;
import com.gg.examples.HibernateExample.model.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * User: adurmaz
 * Date: 5/26/13
 * Time: 11:27 AM
 */
public class HibernateTest {

    @Test
    public void testHibernateSetup(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Foo foo = new Foo();
        foo.setName("xx1");
        session.save(foo);
        transaction.commit();
        session.close();
    }

    @Test
    public void testOneToManyBidirectional(){
        Owner owner = new Owner();
        Owner owner1 = new Owner();

        Pet pet = new Pet();

        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(owner);
        session.save(owner1);
        session.save(pet);

        owner.addPet(pet);

        transaction.commit();
    }
}
