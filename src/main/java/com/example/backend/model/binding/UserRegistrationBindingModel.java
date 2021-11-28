package com.example.backend.model.binding;

import com.example.backend.validator.PasswordsMatch;
import com.example.backend.validator.UniqueUsername;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@PasswordsMatch(
        field="password",
        fieldMatch = "confirmPassword"
)
public class UserRegistrationBindingModel {

    private String username;
    private String nickname;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String groupName;
    private boolean isDriver;

    public UserRegistrationBindingModel() {
    }

    @NotNull
    @UniqueUsername
    @Email(message = "The email is not valid!", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Size(max = 50, message = "The email address must be less then 50 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(max = 40, message = "The nickname must be no larger than 40 symbols!")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @NotNull
    @Size(min = 8, message = "The password must be not smaller then 8 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull
    @Size(max = 50, message = "The first name must be not smaller then 50 characters!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Size(max = 70, message = "The last name must be not smaller then 70 characters!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @NotNull
    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }
}
