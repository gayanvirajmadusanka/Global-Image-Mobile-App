package com.example.globalimage;

public class Customer {

    public String userEmail,userName,userPassword,userPasswordConfirm,userContactNumber,Gender;

    public Customer(){


    }

    public Customer(String userEmail, String userName, String userPassword, String userPasswordConfirm, String userContactNumber, String gender) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPasswordConfirm = userPasswordConfirm;
        this.userContactNumber = userContactNumber;
        Gender = gender;
    }
}
