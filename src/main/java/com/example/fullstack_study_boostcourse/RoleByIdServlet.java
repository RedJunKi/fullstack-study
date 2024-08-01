package com.example.fullstack_study_boostcourse;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/roles/*")
public class RoleByIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RoleByIdServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String idStr = pathParts[1];
        int id = Integer.parseInt(idStr);

        RoleDao dao = new RoleDao();
        Role role = dao.getRole(id);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(role);
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
    }
}
