package com.example.fullstack_study_boostcourse.jdbc_study;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/roles")
public class RolesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RolesServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        RoleDao dao = new RoleDao();
        List<Role> list = dao.getRoles();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);

        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }
}
