package com.epam.star.action;

import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SearchePagination<T, E extends AbstractH2Dao> {
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_ROWS_COUNT = 10;

    public void executePaginationAction(HttpServletRequest request, E genericDao, String pagename, String targetName) throws DaoException {

        int rowsCount = DEFAULT_ROWS_COUNT;
        int pageNumber = DEFAULT_PAGE_NUMBER;

        String pageString = request.getParameter(targetName + "page");
        String rowsString = request.getParameter(targetName + "rows");
        if (rowsString != null) rowsCount = Integer.valueOf(rowsString);
        if (pageString != null) pageNumber = Integer.valueOf(pageString);
        int firstRow = pageNumber * rowsCount - DEFAULT_ROWS_COUNT;

        String desiredValue = request.getParameter("desiredValue");
        String columnName = request.getParameter("columnName");

        List<T> tableList = null;
        int tableLenght;
        boolean isFind = false;
        tableList = genericDao.findRangeWithValue(firstRow, rowsCount, request);
        tableLenght = tableList.size();

        List<Integer> paginationList = new ArrayList<>();
        for (int i = 0; i < tableLenght / rowsCount + 1; i++) {
            paginationList.add(i + 1);
        }

        request.setAttribute(targetName + "Paginationlist", paginationList);
        request.setAttribute(targetName + "List", tableList);
        request.setAttribute(targetName + "PageNumber", pageNumber);
        request.setAttribute(targetName + "RowsCount", rowsCount);
        request.setAttribute(targetName + "Pagename", pagename);

    }
}
