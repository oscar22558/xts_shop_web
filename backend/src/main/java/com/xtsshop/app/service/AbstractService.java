package com.xtsshop.app.service;

import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class AbstractService <R extends Request<E>, E extends AppEntity> {

    protected JpaRepository<E, Long> repository;

    public AbstractService(
        JpaRepository<E, Long> repository
    ){
        this.repository = repository;
    }

    public E get(Long id){
        return repository.findById(id).orElseThrow();
    }

    public List<E> all(){
        return repository.findAll();
    }
    public E create(R request){
       E entity = repository.save(request.toEntity());
       repository.flush();
       return entity;
    }

    public E update(long id, R request){
        return repository.save(
            request.update(
                repository.findById(id).orElseThrow()
            ));
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
