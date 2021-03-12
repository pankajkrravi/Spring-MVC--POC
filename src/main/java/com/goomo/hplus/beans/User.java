package com.goomo.hplus.beans;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    //@Size(min = 6,message = "Username van no be less than 6 characters")
    @Size(min = 6,message = "{username.can.not.be.less.than.six.characters}") //reading error message from ValidationMessage properties file
    private String username;
    @Pattern(regexp ="((?=.*[A-Z]).{6,10})",message = "Password must have one Upper case,one lower case, between 6 -10 character")
    private String password;
    @Enumerated(EnumType.STRING)//To store Actual Enum Value insted of ordinal like 0,1,2, to be stored
    private Gender gender;
    @NotNull(message = "Activity can not be Empty")
    private String activity;
    @NotEmpty(message = "First name can not be empty")
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
