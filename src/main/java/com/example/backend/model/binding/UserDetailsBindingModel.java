package com.example.backend.model.binding;

import com.example.backend.validator.UniqueUsername;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserDetailsBindingModel {

    private String username;
    private String firstName;
    private String lastName;
    private String nickname;
    private String groupName;
    private boolean isActive;
    private boolean tripAdmin;
    private boolean merchAdmin;
    private boolean membershipFeeAdmin;

    public UserDetailsBindingModel() {
    }

    @NotNull
    @Email(message = "The email is not valid!", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Size(max = 50, message = "The email address must be less then 50 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @NotNull
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isTripAdmin() {
        return tripAdmin;
    }

    public void setTripAdmin(boolean tripAdmin) {
        this.tripAdmin = tripAdmin;
    }

    public boolean isMerchAdmin() {
        return merchAdmin;
    }

    public void setMerchAdmin(boolean merchAdmin) {
        this.merchAdmin = merchAdmin;
    }

    public boolean isMembershipFeeAdmin() {
        return membershipFeeAdmin;
    }

    public void setMembershipFeeAdmin(boolean membershipFeeAdmin) {
        this.membershipFeeAdmin = membershipFeeAdmin;
    }
}
