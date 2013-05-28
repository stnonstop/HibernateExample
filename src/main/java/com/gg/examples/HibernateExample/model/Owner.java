package com.gg.examples.HibernateExample.model;

import org.hibernate.annotations.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn
@Table(name="owners")
public class Owner extends Person implements Auditable {
	
	private static final long serialVersionUID = 1L;
	
	private Address address = new Address(this);

	@OneToMany(mappedBy="owner", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.LOCK)
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
