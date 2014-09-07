package com.epam.star.dao.H2dao;

import com.epam.star.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractH2Dao<T extends AbstractEntity> {


    public abstract List<T> findRange(int startRow, int rowsCount);

    public abstract int getAll();
}
