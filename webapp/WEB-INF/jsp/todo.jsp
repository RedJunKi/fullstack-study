<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TODO Application</title>
    <link rel="stylesheet" href="/css/todo-style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>나의 해야할 일들</h1>
            <button id="addTodo">새로운 TODO 등록</button>
        </header>
        <main>
            <div class="column todo">
                <h2>TODO</h2>
                <%-- Iterate over TODO items from a list or database --%>
                <%
                    // Example list of TODO items - this would be fetched from a database in a real application
                    List<Todo> todoItems = (List<Todo>) request.getAttribute("todoItems");
                    if (todoItems != null) {
                        for (Todo item : todoItems) {
                            if (item.getType().equals("TODO")) {
                %>
                <div class="card">
                    <h3><%= item.getTitle() %></h3>
                    <p>등록날짜: <%= item.getDateTime() %>, <%= item.getName() %>, 우선순위 <%= item.getSequence() %></p>
                    <button class="arrow" onclick="moveCard(event, 'doing')">→</button>
                </div>
                <%
                            }
                        }
                    }
                %>
            </div>
            <div class="column doing">
                <h2>DOING</h2>
                <%
                    if (todoItems != null) {
                        for (Todo item : todoItems) {
                            if (item.getType().equals("DOING")) {
                %>
                <div class="card">
                    <h3><%= item.getTitle() %></h3>
                    <p>등록날짜: <%= item.getDateTime() %>, <%= item.getName() %>, 우선순위 <%= item.getSequence() %></p>
                    <button class="arrow" onclick="moveCard(event, 'done')">→</button>
                </div>
                <%
                            }
                        }
                    }
                %>
            </div>
            <div class="column done">
                <h2>DONE</h2>
                <%
                    if (todoItems != null) {
                        for (Todo item : todoItems) {
                            if (item.getType().equals("DONE")) {
                %>
                <div class="card">
                    <h3><%= item.getTitle() %></h3>
                    <p>등록날짜: <%= item.getDateTime() %>, <%= item.getName() %>, 우선순위 <%= item.getSequence() %></p>
                </div>
                <%
                            }
                        }
                    }
                %>
            </div>
        </main>
    </div>
    <script src="scripts.js"></script>
</body>
</html>
