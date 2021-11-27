package com.example.backend.models.binding;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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
