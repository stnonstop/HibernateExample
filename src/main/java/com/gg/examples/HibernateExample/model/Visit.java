package com.gg.examples.HibernateExample.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Index;

@Entity
@Table(name="visits")
@org.hibernate.annotations.Table(indexes={@Index(name="visit_date_idx",columnNames={"visit_date"})}, appliesTo = "visits")
public class Visit extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="visit_date")
	private Date date;

	@Column(name="description")
	private String description;

	@ManyToOne
	@JoinColumn(name="pet_id",insertable=false,updatable=false,nullable=false)
	private Pet pet;

	@Column(name="checkup")
	private Boolean checkup = false;

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Pet getPet() {
		return this.pet;
	}

	protected void setPet(Pet pet) {
		this.pet = pet;
	}

	public Boolean getCheckup() {
		return checkup;
	}

	public void setCheckup(Boolean checkup) {
		this.checkup = checkup;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!Visit.class.isAssignableFrom(o.getClass()))
			return false;
		Visit v = (Visit) o;
		return new EqualsBuilder().append(getDate(), v.getDate())
				.append(getPet(), v.getPet())
				.append(getCheckup(), v.getCheckup()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getDate()).append(getPet())
				.toHashCode();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);
	}
}
