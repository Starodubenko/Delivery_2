package com.epam.star.util;

public class Validator {
    private final String USER_NAME = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private final String USER_PASSWORD = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private final String DATE = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d";
    private final String TIME = "^([0-1]\\d|2[0-3])(:[0-5]\\d){2}$";
    private final String NUMBER = "(\\d{1,})?";
    private final String E_mail = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";


}
