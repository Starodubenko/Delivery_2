package com.epam.star;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {

        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("10-08-2014");

        java.sql.Date date1 = new java.sql.Date(date.getTime());
        System.out.println(date);
        System.out.println(date1);
    }
}
