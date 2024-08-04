package com.example.fullstack_study_boostcourse.project_b;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@RequestMapping
@Controller
public class TodoServlet {
    @GetMapping("/todo")
    public String getTodos(HttpServletRequest request){
        TodoDao todoDao = new TodoDao();
        List<Todo> todos = todoDao.getTodos();
        request.setAttribute("todoItems", todos);

        return "todo.html";
    }
}
