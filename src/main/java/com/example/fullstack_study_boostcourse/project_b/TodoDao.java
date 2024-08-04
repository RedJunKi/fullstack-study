package com.example.fullstack_study_boostcourse.project_b;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private static String dburl = "jdbc:mysql://localhost:3306/mydatabase";
    private static String dbUser = "connectuser";
    private static String dbpasswd = "connect123!@#";

    public int addTodo(TodoDto todoDto) {
        int result = 0;

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            String sql = "INSERT INTO todo (title, name, sequence, type, regdate) VALUES ( ?, ?, ?, ?, ? )";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, todoDto.getTitle());
            stmt.setString(2, todoDto.getName());
            stmt.setInt(3, todoDto.getSequence());
            stmt.setString(4, "TODO");
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, stmt, null);
        }

        return result;
    }

    public List<Todo> getTodos() {
        List<Todo> result = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String sql = "SELECT * FROM todo";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int sequence = rs.getInt("sequence");
                String title = rs.getString("title");
                String name = rs.getString("name");

                Todo todo = new Todo(title, name, sequence);
                result.add(todo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, stmt, rs);
        }
        return result;
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

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, dbUser, dbpasswd);
    }
}
