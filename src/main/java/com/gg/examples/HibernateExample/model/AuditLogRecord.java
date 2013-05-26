package com.gg.examples.HibernateExample.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AuditLogRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public String message;
	
	public Long entityId;
	
	public Class<? extends BaseEntity> entityClass;
	
	public Long userId;
	
    	public Date created;

	AuditLogRecord() {}

	public AuditLogRecord(String message,
						  Long entityId,
						  Class<? extends BaseEntity> entityClass,
						  Long userId) {
		this.message = message;
		this.entityId = entityId;
		this.entityClass = entityClass;
		this.userId = userId;
		this.created = new Date();
	}
    
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

	public String getMessage() {
		return message;
	}

	public Long getEntityId() {
		return entityId;
	}

	public Class<? extends BaseEntity> getEntityClass() {
		return entityClass;
	}

	public Long getUserId() {
		return userId;
	}

	public Date getCreated() {
		return created;
	}
}
