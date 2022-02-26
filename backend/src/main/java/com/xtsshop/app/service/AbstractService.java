package com.xtsshop.app.service;

import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.request.CategoryForm;
import com.xtsshop.app.request.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class AbstractService <F extends Form<E>, E extends AppEntity> {

    Logger logger = LoggerFactory.getLogger(AbstractService.class);
    protected JpaRepository<E, Long> repository;

    public AbstractService(
        JpaRepository<E, Long> repository
    ){
        this.repository = repository;
    }

    public E get(Long id){
        return repository.getById(id);
    }

    public List<E> all(){
        return repository.findAll();
    }
    public void create(F form){
        repository.save(form.toEntity());
    }

    public void update(long id, F form){
        repository.save(
            form.update(
                repository.getById(id)
            ));
    }

    public void delete(long id){
        E entity = repository.getById(id);
        repository.delete(entity);
    }
}
