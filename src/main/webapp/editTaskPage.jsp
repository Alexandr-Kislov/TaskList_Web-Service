<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 7/11/2020
  Time: 1:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование задачи</title>
</head>
<body>

    <h2>Внесите изменения в вашу задачу</h2>

    <form method="post" action="<c:url value='/edit'/>">

        <label>Название <input type="text" name="name" value="<c:out value="${requestScope.tasks.name}"/>"></label><br>
        <label>Описание <input type="text" name="description" value="<c:out value="${requestScope.tasks.description}"/>"></label><br>
        <label>Исполнитель <input type="text" name="executor" value="<c:out value="${requestScope.tasks.executor}"/>"></label><br>
        <label>Статус
        <select name="status">
            <c:if test="${requestScope.tasks.status == false}">
                <option selected value="false">В процессе</option>
                <option value="true">Выполнена</option>
            </c:if>
            <c:if test="${requestScope.tasks.status == true}">
                <option  value="false">В процессе</option>
                <option selected value="true">Выполнена</option>
            </c:if>
        </select>
        </label><br>

        <label>Срок исполнения <input type="date" name="deadline"
                                      value="<c:out value="${requestScope.tasks.deadline}"/>"></label><br>
        <input type="number" hidden name="id" value="${requestScope.tasks.taskId}"/>
        <input type="text" hidden name="login" value="${requestScope.tasks.login}"/>

        <input type="submit" value="Изменить" name="Ok"><br>
    </form>

</body>
</html>
