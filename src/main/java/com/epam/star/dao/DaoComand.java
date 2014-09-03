package com.epam.star.dao;

import com.epam.star.dao.h2dao.DaoManager;

public interface DaoComand {
    public Object execute(DaoManager daoManager);
}
