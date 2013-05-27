package com.gg.examples.HibernateExample.dao;

import com.gg.examples.HibernateExample.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;

/**
 * User: adurmaz
 * Date: 5/27/13
 * Time: 3:12 PM
 */
public class PetClinicDaoHibernate implements PetClinicDao {
    @Override
    public Collection<Vet> getVets() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<Owner> findOwners(String lastName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<Visit> findVisits(long petId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<Person> findAllPersons() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Owner loadOwner(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Owner owner = (Owner) session.load(Owner.class, id);
        //Hibernate.initialize(owner);
        owner.getId();
        session.close();
        return owner;
    }

    @Override
    public Pet loadPet(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Pet pet = (Pet) session.get(Pet.class, id);
        session.close();
        return pet;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Vet loadVet(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Vet vet = (Vet) session.createQuery("FROM Vet WHERE Vet.id = ? ")
                                .setParameter(0,id).uniqueResult();
        session.close();
        return vet;
    }

    @Override
    public void saveOwner(Owner owner) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();
        session.close();
    }

    @Override
    public void saveVet(Vet vet) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(vet);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteOwner(long ownerId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(Owner.class,ownerId));
        transaction.commit();
        session.close();
    }
}
