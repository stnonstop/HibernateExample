package com.gg.examples.HibernateExample.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(name = "OWNER_ID")
public class Owner extends Person {
	
	private static final long serialVersionUID = 1L;
	
	private Address address = new Address(this);

    @OneToMany(mappedBy = "owner")
	private Set<Pet> pets = new HashSet<Pet>();
	
	public Set<Pet> getPets() {
		return Collections.unmodifiableSet(pets);
        //return pets;
	}
	
	public void addPet(Pet pet) {
		pet.setOwner(this);
		pets.add(pet);
	}

	public Address getAddress() {
		return address;
	}
}
