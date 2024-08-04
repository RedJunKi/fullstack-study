package com.example.fullstack_study_boostcourse.springjdbc_study;

public class Role {
    private int roleId;
    private String description;

    public Role() {

    }

    public Role(int roleId, String description) {
        this.roleId = roleId;
        this.description = description;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ROLE [roleId=" + roleId + " desc=" + description + "]";
    }

}
