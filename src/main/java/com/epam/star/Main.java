package com.epam.star;

import com.epam.star.entity.Period;

import java.sql.SQLException;
import java.sql.Time;

public class Main {
    public static void main(String[] args) throws SQLException {
        Period period = new Period();
        period.setPeriod(new Time(14,00,00));
        System.out.println("Time is " + period.getPeriod());
    }
}
