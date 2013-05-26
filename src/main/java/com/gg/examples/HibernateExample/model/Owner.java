package com.gg.examples.HibernateExample.model;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Owner extends Person {
	
	private static final long serialVersionUID = 1L;
	
	private Address address = new Address(this);

    @Transient
	private Set<Pet> pets = new HashSet<Pet>();
	
	public Set<Pet> getPets() {
		return Collections.unmodifiableSet(pets);
	}
	
	public void addPet(Pet pet) {
		pet.setOwner(this);
		pets.add(pet);
	}

	public Address getAddress() {
		return address;
	}
}
