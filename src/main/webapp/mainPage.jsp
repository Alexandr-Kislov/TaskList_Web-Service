<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 7/10/2020
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список задач</title>
</head>
<body>

    <h2>С возвращением, <c:out value="${sessionScope.userName}"/> </h2><br/>

    <form method="get" action="<c:url value='/exit'/>">
        <input type="submit" name="exit" value="Выйти"/>
    </form>
    <hr>

    <h2>Добавить новую задачу</h2>
    <form method="post" action="<c:url value='/add'/>">

        <label>Название <input type="text" name="name"></label><br>
        <label>Описание <input type="text" name="description"></label><br>
        <label>Исполнитель <input type="text" name="executor"></label><br>
        <select name="status">
            <option value="false">В процессе</option>
            <option value="true">Выполнена</option>
        </select>
        <input type="date" name="deadline">

        <input type="submit" value="Добавить" name="Ok"><br>
    </form>
    <hr>

    <h2>Ваш список задач:</h2><br/>

    <c:if test="${requestScope.tasks.size()==0}">
        У вас пока нет ни одной задачи
    </c:if>

    <c:forEach var="task" items="${requestScope.tasks}">

        <div style="border: solid 1px black;">
            Название: <c:out value="${task.name}"/> <br>
            Описание: <c:out value="${task.description}"/> <br>
            Исполнитель: <c:out value="${task.executor}"/> <br>
            Статус:
            <c:if test="${task.status == false}">
                <i style="color: red"> В процессе </i>
            </c:if>
            <c:if test="${task.status == true}">
                <i style="color: green">Выполнена </i>
            </c:if>
            <br>
            Срок выполнения: <c:out value="${task.deadline}"/> <br>

            <form method="get" action="<c:url value='/edit'/>">
                <input type="number" hidden name="id" value="${task.taskId}"/>
                <input type="text" hidden name="login" value="${task.login}"/>
                <input type="submit" value="Изменить"/>
            </form>
            <form method="post" action="<c:url value='/delete'/>">
                <input type="number" hidden name="id" value="${task.taskId}"/>
                <input type="text" hidden name="login" value="${task.login}"/>
                <input type="submit" name="delete" value="Удалить"/>
            </form>
        </div>

    </c:forEach>

</body>
</html>
