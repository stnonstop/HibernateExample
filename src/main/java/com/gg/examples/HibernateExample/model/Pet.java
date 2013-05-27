package com.gg.examples.HibernateExample.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.*;


@Entity
@Table(name="pets")
public class Pet extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name="name")
	private String name;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name="birth_date")
	private Date birthDate;

	@ManyToOne
	@ForeignKey(name="pet_type_fk")
	@JoinColumn(name="type_id")
	private PetType type;

	@ManyToOne
	@JoinColumn(name="owner_id")
	private Owner owner;

	@OneToMany(orphanRemoval=true)
	@JoinColumn(name="pet_id")
	@OrderColumn(name="pos_index")
    @LazyCollection(LazyCollectionOption.EXTRA)
	private List<Visit> visits = new ArrayList<Visit>();
	
	@ElementCollection
	@JoinTable(name="images",joinColumns={@JoinColumn(name="pet_id")})
	@MapKeyColumn(name="image_name")
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
