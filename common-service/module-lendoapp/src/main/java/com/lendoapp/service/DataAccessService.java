package com.lendoapp.service;

import com.lendoapp.dao.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class DataAccessService<A> {
    @Autowired
    private Repository repository;

    // Methods for reading data

    public Iterable<A> read(Class type){
        Iterable<A> result = repository.read(type);
        return result;
    }

    public A read(Class type, Long resourceId){
        A result = (A) repository.read(type, resourceId);
        return result;
    }

    public Iterable<A> read(String query, HashMap<String,Object> params){
        return repository.read(query, params);
    }

    public Iterable<A> readNative(String query, HashMap<String,Object> params){
        return repository.readNative(query, params);
    }

    public Iterable<A> readNative(Class clazz, String query, HashMap<String,Object> params){
        return repository.readNative(clazz, query, params);
    }

    // Methods to store data

    public A create(A a){
        return (A) repository.create(a);
    }

    public List<A> create(List<A> objList){
        return repository.create(objList);
    }

    // Methods to update data

    public A update(A a){
        return (A) repository.update(a);
    }

    // Methods to delete data

    public void delete(String query, HashMap<String,Object> params){
        repository.delete(query,params);
    }

    public void delete(Class type, Long resourceId){
        repository.delete(type, resourceId);
    }


}
