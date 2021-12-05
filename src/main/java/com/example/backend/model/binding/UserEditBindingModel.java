package com.example.backend.model.binding;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;

public class UserEditBindingModel {

    private String firstName;
    private String lastName;
    private String nickname;

    public UserEditBindingModel() {
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

    @Size(max = 40, message = "The nickname must be no larger than 40 symbols!")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
