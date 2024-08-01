package com.example.fullstack_study_boostcourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {
    private static String dburl = "jdbc:mysql://localhost:3306/mydatabase";
    private static String dbUser = "connectuser";
    private static String dbpasswd = "connect123!@#";

    public List<Role> getRoles() {
        List<Role> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Role role = null;

        try {
            con = getConnection();
            String sql = "SELECT * FROM ROLE";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                String description = rs.getString("description");
                role = new Role(roleId, description);
                result.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, stmt, rs);
        }
        return result;
    }

    public int updateRole(int roleId, String description) {
        int result = 0;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            String sql = "UPDATE ROLE SET description = ? WHERE role_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, description);
            stmt.setInt(2, roleId);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, stmt, null);
        }

        return result;
    }

    public int deleteRole(int roleId) {
        int result = 0;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            String sql = "DELETE FROM ROLE WHERE role_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, roleId);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, stmt, null);
        }
        return result;
    }

    public int addRole(Role role) {
        int result = 0;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            String sql = "INSERT INTO ROLE (role_id, description) VALUES ( ?, ? ) ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, role.getRoleId());
            stmt.setString(2, role.getDescription());
            result = stmt.executeUpdate();
        } catch (Exception e) {

        } finally {
            close(con, stmt, null);
        }
        return result;
    }

    public Role getRole(int roleId) {
        Role result = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String sql = "SELECT role_id, description FROM ROLE WHERE role_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, roleId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String description = rs.getString(2);
                result = new Role(id, description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, stmt, rs);
        }
        return result;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, dbUser, dbpasswd);
    }

    private static void close(Connection con, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
