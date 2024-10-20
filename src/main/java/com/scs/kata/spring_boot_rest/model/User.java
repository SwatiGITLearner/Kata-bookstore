package com.scs.kata.spring_boot_rest.model;
import jakarta.persistence.*;
import lombok.AccessLevel;



@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String userName;

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEncPassword() {
        return encPassword;
    }

    @Column
    private String emailAddress;
    @Column
    private String encPassword;

    public User(String userName, String emailAddress, String encPassword) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.encPassword = encPassword;
    }
}
