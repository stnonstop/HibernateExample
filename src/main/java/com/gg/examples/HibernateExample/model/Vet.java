package com.gg.examples.HibernateExample.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Vet extends Person {
	
	private static final long serialVersionUID = 1L;

	private Set<Specialty> specialties = new HashSet<Specialty>();

	public Set<Specialty> getSpecialties() {
		return Collections.unmodifiableSet(specialties);
	}
	
	public void addSpecialty(Specialty specialty) {
		specialties.add(specialty);
	}
}
