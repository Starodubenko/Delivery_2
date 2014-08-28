package com.epam.star.entity;

public class Employee extends AbstractUser{
    private String mobilephone;

    public Employee() {

    }

    public Employee(int id, String login, String password, String firstName, String lastName, String middleName, String address, String telephone, String mobilephone) {
        super(id, login, password, firstName, lastName, middleName, address, telephone);
        this.mobilephone = mobilephone;
    }

    public String getMobilephone() {

        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
}
