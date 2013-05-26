package com.gg.examples.HibernateExample.dao;

import java.util.Collection;

import com.gg.examples.HibernateExample.model.Owner;
import com.gg.examples.HibernateExample.model.Person;
import com.gg.examples.HibernateExample.model.Pet;
import com.gg.examples.HibernateExample.model.Vet;
import com.gg.examples.HibernateExample.model.Visit;


public interface PetClinicDao {
	
	Collection<Vet> getVets();

	Collection<Owner> findOwners(String lastName);
	
	Collection<Visit> findVisits(long petId);
	
	Collection<Person> findAllPersons();

	Owner loadOwner(long id);

	Pet loadPet(long id);
	
	Vet loadVet(long id);

	void saveOwner(Owner owner);

	void saveVet(Vet vet);

	void deleteOwner(long ownerId);
}
