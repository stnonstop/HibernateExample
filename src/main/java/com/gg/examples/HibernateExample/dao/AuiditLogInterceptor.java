package com.gg.examples.HibernateExample.dao;

import com.gg.examples.HibernateExample.model.AuditLogRecord;
import com.gg.examples.HibernateExample.model.Auditable;
import com.gg.examples.HibernateExample.model.BaseEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * User: adurmaz
 * Date: 5/28/13
 * Time: 4:06 PM
 */
public class AuiditLogInterceptor extends EmptyInterceptor {

    public void insertAuditLogRecord(AuditLogRecord auditLogRecord){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        session.save(auditLogRecord);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {

        if(entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            AuditLogRecord auditLogRecord = new AuditLogRecord("UPDATED", auditable.getId(), (Class<? extends BaseEntity>)auditable.getClass(), 1L);
            insertAuditLogRecord(auditLogRecord);
        }
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if(entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            AuditLogRecord auditLogRecord = new AuditLogRecord("INSERTED", auditable.getId(), (Class<? extends BaseEntity>)auditable.getClass(), 1L);
            insertAuditLogRecord(auditLogRecord);
        }
        return super.onSave(entity, id, state, propertyNames, types);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if(entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            AuditLogRecord auditLogRecord = new AuditLogRecord("DELETED", auditable.getId(), (Class<? extends BaseEntity>)auditable.getClass(), 1L);
            insertAuditLogRecord(auditLogRecord);
        }
        super.onDelete(entity, id, state, propertyNames, types);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
