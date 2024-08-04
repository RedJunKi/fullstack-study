package com.example.fullstack_study_boostcourse.springjdbc_study;

public class RoleDaoSqls {
    public static final String SELECT_ALL = "SELECT role_id, description FROM ROLE ORDER BY role_id";
    public static final String INSERT = "INSERT INTO ROLE (role_id, description) values (:roleId, :description)";
    public static final String UPDATE = "UPDATE ROLE set description = :description where role_id = :roleId";
    public static final String SELECT = "SELECT role_id, description FROM ROLE where role_id = :roleId";
    public static final String DELETE = "DELETE FROM ROLE where role_id = :roleId";
}
