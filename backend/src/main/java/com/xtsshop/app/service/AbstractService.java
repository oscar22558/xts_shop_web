package com.xtsshop.app.service;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
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

    public E get(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id " + id + " not found."));
    }

    public List<E> all(){
        return repository.findAll();
    }
    public E create(R request){
       return repository.save(request.toEntity());
    }

    public E update(long id, R request) throws RecordNotFoundException{
        return repository.save(
            request.update(
                get(id)
            ));
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
