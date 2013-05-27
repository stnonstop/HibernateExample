package com.gg.examples.HibernateExample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="persons",uniqueConstraints={@UniqueConstraint(name="person_constraint",columnNames={"FIRST_NAME","LAST_NAME"})})
public abstract class Person extends BaseEntity implements Auditable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if(this == o) return true;
		if (!Person.class.isAssignableFrom(o.getClass()))
			return false;
		Person p = (Person) o;
		return new EqualsBuilder().append(getFirstName(), p.getFirstName())
				.append(getLastName(), p.getLastName()).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(getFirstName()).append(getLastName()).toHashCode();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SIMPLE_STYLE);
	}
}
