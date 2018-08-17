package com.gravity.paydebt.model;

import javax.persistence.*;

@Entity
@Table(name="user_info")
public class UserInfo {

    @Id
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String address;
    private String email;
    private String tell;
    @Transient
    @OneToOne
    @JoinColumn(name="username")
    private UserPwd userPwd;

    public UserInfo() {
    }

    public UserInfo(String username, String firstName, String lastName, String address, String email, String tell, com.gravity.paydebt.model.UserPwd userPwd) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.tell = tell;
        this.userPwd = userPwd;
    }


    public UserPwd getUserPwd() {
        return userPwd;
    }

    public UserInfo setUserPwd(UserPwd userPwd) {
        this.userPwd = userPwd;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserInfo setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserInfo setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTell() {
        return tell;
    }

    public UserInfo setTell(String tell) {
        this.tell = tell;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", tell='" + tell + '\'' +
                ", UserPwd=" + userPwd +
                '}';
    }
}
