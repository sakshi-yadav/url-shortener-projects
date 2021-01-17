package com.lendoapp.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class Repository<A> {
    @Autowired
    private PersistenceRepository persistenceRepository;

    public A create(A a) {
        return (A) persistenceRepository.create(session -> {
            session.save(a);
            return a;
        });
    }

    public List<A> create(List<A> objectList) {
        return (List<A>) persistenceRepository.create(session -> {
            for (A a : objectList) {
                session.save(a);
            }
            return objectList;
        });
    }

    public List<A> read(Class clazz) {
        return (List<A>) persistenceRepository.read(session -> {
            Query query = session.createQuery("from" + clazz.getSimpleName());
            return (List<A>) query.list();
        });
    }

    public A read(Class clazz, Long id) {
        return (A) persistenceRepository.read(session -> {
            return session.get(clazz, id);
        });
    }

    public List<A> readNative(Class clazz, String strQuery, HashMap<String, Object> params) {
        return (List<A>) persistenceRepository.read(session -> {
            Query<A> query = session.createNativeQuery(strQuery, clazz);
            params.forEach(query::setParameter);
            return query.list();
        });
    }

    public List<A> readNative(String strQuery, HashMap<String, Object> params) {
        return (List<A>) persistenceRepository.read(session -> {
            Query<A> query = session.createNativeQuery(strQuery);
            params.forEach(query::setParameter);
            return query.list();
        });
    }

    public List<A> read(String strQuery, HashMap<String, Object> params) {
        return (List<A>) persistenceRepository.read(session -> {
            Query<A> query = session.createQuery(strQuery);
            params.forEach(query::setParameter);
            return query.list();
        });
    }

    public void delete(String strQuery, HashMap<String, Object> params) {
        persistenceRepository.delete(session -> {
            Query<A> query = session.createQuery(strQuery);
            params.forEach(query::setParameter);
            query.executeUpdate();
        });
    }

    public void delete(Class clazz, Long resourceId) {
        persistenceRepository.delete(session -> {
            A a = (A) session.get(clazz, resourceId);
            if (Objects.nonNull(a)) {
                session.delete(a);
            }
        });
    }

    public A update(A a) {
        return (A) persistenceRepository.update(session -> {
            session.saveOrUpdate(a);
            return a;
        });
    }

}
