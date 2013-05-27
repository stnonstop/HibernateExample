package com.gg.examples.HibernateExample.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="vets")
@PrimaryKeyJoinColumn
public class Vet extends Person {
	
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name="vet_specialties",joinColumns={@JoinColumn(name="vets_id")},inverseJoinColumns={@JoinColumn(name="specialties_id")})
	@ForeignKey(name="vet_specialty_fk")
	private Set<Specialty> specialties = new HashSet<Specialty>();

	public Set<Specialty> getSpecialties() {
		return Collections.unmodifiableSet(specialties);
	}
	
	public void addSpecialty(Specialty specialty) {
		specialties.add(specialty);
	}
}
