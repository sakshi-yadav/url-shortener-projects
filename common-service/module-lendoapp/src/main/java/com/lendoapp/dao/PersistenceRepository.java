package com.lendoapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersistenceRepository {
    @Autowired
    @Qualifier("postgreSessionFactory")
    private SessionFactory sessionFactory;

    @FunctionalInterface
    public interface FunctionalCreate {
        Object persist(Session session);
    }

    @FunctionalInterface
    public interface FunctionalRead {
        Object read(Session session);
    }

    @FunctionalInterface
    public interface FunctionalUpdate {
        Object update(Session session);
    }

    @FunctionalInterface
    public interface FunctionalDelete {
        void delete(Session session);
    }

    public Object create(FunctionalCreate functionalCreate) {
        Transaction transaction = null;
        Session session = null;
        Object object;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            object = functionalCreate.persist(session);
            transaction.commit();
        } catch (Exception e) {
            if (Objects.nonNull(transaction))
                transaction.rollback();
            throw e;
        } finally {
            closeSession(session);
        }

        return object;
    }

    public Object read(FunctionalRead functionalRead) {
        Session session = null;
        Object object;
        try {
            session = sessionFactory.openSession();
            object = functionalRead.read(session);
        } catch (Exception e) {
            throw e;
        } finally {
            closeSession(session);
        }

        return object;
    }

    public Object update(FunctionalUpdate functionalUpdate) {
        Transaction transaction = null;
        Session session = null;
        Object object;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            object = functionalUpdate.update(session);
            transaction.commit();
        } catch (Exception e) {
            if (Objects.nonNull(transaction))
                transaction.rollback();
            throw e;
        } finally {
            closeSession(session);
        }

        return object;
    }

    public void delete(FunctionalDelete functionalDelete) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            functionalDelete.delete(session);
            transaction.commit();
        } catch (Exception e) {
            if (Objects.nonNull(transaction))
                transaction.rollback();
            throw e;
        } finally {
            closeSession(session);
        }
    }

    private void closeSession(Session session) {
        if (Objects.nonNull(session))
            session.close();
    }
}
