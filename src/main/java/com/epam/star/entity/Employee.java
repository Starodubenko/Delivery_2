package com.epam.star.entity;

public class Employee extends AbstractUser{
    private String mobilephone;
    private String identityCard;
    private String workBook;
    private String RNN;

    public Employee(int id, String login, String password, String firstName, String lastName, String middleName, String address, String telephone, String mobilephone) {
        super(id, login, password, firstName, lastName, middleName, address, telephone);
        this.mobilephone = mobilephone;
    }

    public String getSIK() {
        return SIK;
    }

    public void setSIK(String SIK) {
        this.SIK = SIK;
    }

    public String getRNN() {
        return RNN;
    }

    public void setRNN(String RNN) {
        this.RNN = RNN;
    }

    public String getWorkBook() {
        return workBook;
    }

    public void setWorkBook(String workBook) {
        this.workBook = workBook;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    private String SIK;

    public Employee() {

    }

    public String getMobilephone() {

        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
}
