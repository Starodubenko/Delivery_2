package com.epam.star.action;


import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoException;
import com.epam.star.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Pagination<T, E extends AbstractH2Dao> {
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_ROWS_COUNT = 5;

    public void executePaginationAction(HttpServletRequest request, ClientDao genericDao, String pagename) throws DaoException {

        int pageNumber = DEFAULT_PAGE_NUMBER;
        int rowsCount = DEFAULT_ROWS_COUNT;
        String pageString = request.getParameter("page");
        String rowsString = request.getParameter("rows");
        if (pageString != null) pageNumber = Integer.valueOf(pageString);
        if (rowsString != null) rowsCount = Integer.valueOf(rowsString);
        List<Client> tableList = genericDao.findRange(pageNumber, rowsCount);
        List<Client> pagLenghtList = genericDao.getAll();
        int tableLenght = pagLenghtList.size();
        List<Integer> paginationList = new ArrayList<>();
        for (int i = 0; i < tableLenght / rowsCount + 1; i++) {
            paginationList.add(i + 1);
        }
        if (pageNumber == tableLenght / rowsCount + 1) {
            request.setAttribute("nextdisabled", "disabled");
        }
        if (pageNumber == 1) {
            request.setAttribute("backdisabled", "disabled");
        }
        request.setAttribute("paginationlist", paginationList);
        request.setAttribute("list", tableList);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("rowsCount", rowsCount);
        request.setAttribute("pagename", pagename);

    }
}


