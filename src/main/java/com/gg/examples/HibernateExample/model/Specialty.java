package com.gg.examples.HibernateExample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


@Entity
@Table(name="specialties")
public class Specialty extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if(this == o) return true;
		if (!Specialty.class.isAssignableFrom(o.getClass()))
			return false;
		Specialty s = (Specialty) o;
		return new EqualsBuilder().append(getName(), s.getName()).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).toHashCode();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SIMPLE_STYLE);
	}
}	
