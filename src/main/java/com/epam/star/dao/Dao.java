package com.epam.star.dao;

import com.epam.star.entity.AbstractEntity;

public interface Dao<T extends AbstractEntity>{

    public T getElement(int ID);
    public String insert(T entity);
    public String deleteElement(int ID);
    public String updateElement(int ID);
}
