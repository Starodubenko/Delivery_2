package com.epam.star.action;

import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class SearchePagination<T, E extends AbstractH2Dao> {
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_ROWS_COUNT = 10;

    private static Map<String, String> fieldsMap = new HashMap<>();

    static {
        fieldsMap.put("order-id", " orders.id = ?");
        fieldsMap.put("order-date", " orders.order_date = ?");
        fieldsMap.put("order-goods-name", " goods.goods_name = ?");
        fieldsMap.put("order-cost", " orders.order_cost = ?");
        fieldsMap.put("delivery-date", " orders.delivery_date = ?");
        fieldsMap.put("delivery-time", " period.period = ?");
        fieldsMap.put("order-addInfo", " orders.additional_info = ?");
        fieldsMap.put("order-status", " status.status_name = ?");
    }

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
        Map<String, String> parametersMap = createQuerryString(request);
        tableList = genericDao.findRangeWithValue(firstRow, rowsCount, parametersMap);
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

    private Map<String, String> createQuerryString(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        Map<String, String[]> parameterMap = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            result.put(entry.getKey(), entry.getValue()[0]);
        }

        return result;
    }
}
