package com.gg.examples.HibernateExample;

import com.gg.examples.HibernateExample.dao.HibernateUtils;
import com.gg.examples.HibernateExample.dao.PetClinicDaoHibernate;
import com.gg.examples.HibernateExample.model.*;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

/**
 * User: adurmaz
 * Date: 5/26/13
 * Time: 11:27 AM
 */
public class HibernateTest {

    private  PetClinicDaoHibernate petClinicDaoHibernate;

    @Before
    public void setup(){
        petClinicDaoHibernate = new PetClinicDaoHibernate();
    }

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
        /*Pet pet = (Pet)session.get(Pet.class,7l);
        System.out.println("before get visits");
        PetType petType = pet.getType();
        petType.getName(); */

        System.out.println("-----------------------");

        Pet myPet = (Pet) session.createQuery("FROM Pet p WHERE p.id = ? ").setParameter(0,7l).uniqueResult();

    }

    @Test
    public void testDeleteOwner(){
        PetClinicDaoHibernate petClinicDaoHibernate = new PetClinicDaoHibernate();
        petClinicDaoHibernate.deleteOwner(1000L);
    }

    @Test
    public void testOptLocking(){
        Session session1 = HibernateUtils.getSessionFactory().openSession();
        Session session2 = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction1 = session1.beginTransaction();
        Transaction transaction2 = session2.beginTransaction();

        Owner owner1 = (Owner)session1.get(Owner.class, 7L);
        Owner owner2 = (Owner)session2.get(Owner.class, 7L);
        owner1.setLastName("Owner1");
        owner2.setLastName("Owner2");

        transaction2.commit();
        System.out.println("After Tx2 commit");
        transaction1.commit();
    }

    @Test
    public void testHQL() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Owner> ownerList = session.
                createQuery("select distinct o from Owner o " +
                        "join fetch o.pets p " +
                        "left join fetch p.visits " +
                        "left join fetch p.imagesByName " +
                        "where p.name LIKE 'L%'").list();
        session.close();
        System.out.println(ownerList.get(0).getPets());
    }


    @Test
    public void testGetVets(){
        Collection<Vet> vetCollection = petClinicDaoHibernate.getVets();
        for(Vet vet : vetCollection){
            System.out.println(vet);
        }
    }

    @Test
    public void testFindOwners(){
        Collection<Owner> ownerCollection = petClinicDaoHibernate.findOwners("Davis");
        for(Owner owner: ownerCollection) {
            System.out.println(owner);
        }
    }

    @Test
    public void testFindVisits(){
        Collection<Visit> visitCollection = petClinicDaoHibernate.findVisits(7L);
        for(Visit visit : visitCollection){
            System.out.println(visit.getPet().getName() + " " + visit.getPet().getType() + " " + visit.getDate() + " " + visit.getDescription());
        }
    }

    @Test
    public void testConversation(){
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        Session session1 = HibernateUtils.getSessionFactory().getCurrentSession();
        Assert.assertSame(session, session1);

        session.beginTransaction().commit();

        Session session2 = HibernateUtils.getSessionFactory().getCurrentSession();
        Assert.assertNotSame(session, session2);

    }

    @Test
    public void testCache(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Session session2 = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        Transaction transaction2 = session2.beginTransaction();

        session.createQuery("from PetType").setCacheable(true).list();
        System.out.println("Before Second Query");
        session2.createQuery("from PetType ").setCacheable(true).list();

        Vet v1 = (Vet)session.get(Vet.class, 3L);
        v1.getSpecialties().size();
        System.out.println("Before Second Query");
        Vet v2 = (Vet)session2.get(Vet.class, 3L);
        v2.getSpecialties().size();


        transaction.commit();
        transaction2.commit();
        session.close();
        session2.close();
    }

}
