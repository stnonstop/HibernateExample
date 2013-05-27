package com.gg.examples.HibernateExample.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

@Entity
public class Pet extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private String name;

    @Temporal(TemporalType.DATE)
	private Date birthDate;

    @ManyToOne
    @ForeignKey(name = "FK_PET_TYPE")
	private PetType type;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
	private Owner owner;

    @OneToMany(orphanRemoval = true)
    @OrderColumn(name = "POSITION")
    @JoinColumn(name = "PET_ID")
	private List<Visit> visits = new ArrayList<Visit>();

    @ElementCollection
    @MapKeyColumn(name = "KEY_COLUMN")
	private Map<String,Image> imagesByName = new HashMap<String, Image>();

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	public PetType getType() {
		return this.type;
	}

	protected void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Owner getOwner() {
		return this.owner;
	}

	@SuppressWarnings("unchecked")
	public List<Visit> getVisits() {
		return Collections.unmodifiableList(visits);
	}

	public void addVisit(Visit visit) {
		this.visits.add(visit);
		visit.setPet(this);
	}
	
	public void removeVisit(Visit visit) {
		this.visits.remove(visit);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Image> getImagesByName() {
		return Collections.unmodifiableMap(imagesByName);
	}
	
	public void addImage(Image image) {
		imagesByName.put(image.getFilename(), image);
	}
	
	public void removeImage(Image image) {
		imagesByName.remove(image.getFilename());
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if(this == o) return true;
		if (!Pet.class.isAssignableFrom(o.getClass()))
			return false;
		Pet p = (Pet) o;
		return new EqualsBuilder().append(getName(), p.getName())
				.append(getType(), p.getType()).append(getOwner(), p.getOwner()).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).append(getType()).append(getOwner()).toHashCode();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SIMPLE_STYLE);
	}
}
