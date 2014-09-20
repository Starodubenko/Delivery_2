package com.epam.star.dao;

import com.epam.star.entity.AbstractEntity;

public interface Dao<T extends AbstractEntity> {

    public T findById(int ID);

    public String insert(T entity);

    public String deleteElement(int ID);

    public String updateElement(T entity);
}
