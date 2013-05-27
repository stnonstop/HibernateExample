package com.gg.examples.HibernateExample;

import com.gg.examples.HibernateExample.dao.HibernateUtils;
import com.gg.examples.HibernateExample.dao.PetClinicDaoHibernate;
import com.gg.examples.HibernateExample.model.*;
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
        Vet vet = new Vet();
        session.save(vet);

        transaction.commit();
    }

    @Test
    public void testSaveOwner(){
        PetClinicDaoHibernate petClinicDaoHibernate = new PetClinicDaoHibernate();
        Owner owner = new Owner();
        owner.setFirstName("Aziz");
        owner.setLastName("DURMAZ");

        Pet pet = new Pet();
        pet.setName("myPet");
        owner.addPet(pet);

        petClinicDaoHibernate.saveOwner(owner);
    }

    @Test
    public void  testLoadOwner(){
        PetClinicDaoHibernate petClinicDaoHibernate = new PetClinicDaoHibernate();
        Owner owner = petClinicDaoHibernate.loadOwner(1000L);
        //Burada LazyInitializationException hatası alınır. Çünkü loadOwner ile session açılıp kapanıyor.
        System.out.println(owner.getFirstName());
    }

    @Test
    public void testFetching(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Pet pet = (Pet)session.get(Pet.class,7l);
        System.out.println("before get visits");
        pet.getVisits().size();

        Visit v = new Visit();
        v.setId(1L);
        pet.getVisits().contains(v);
    }
}
