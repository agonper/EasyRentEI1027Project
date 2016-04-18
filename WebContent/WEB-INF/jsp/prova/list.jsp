<%--
  Created by IntelliJ IDEA.
  User: alberto
  Date: 13/04/16
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Gestionar Proves</title>
</head>
<body>
    <h1>Llista de Proves</h1>
    <table>
        <tr>
            <th>Nom</th>
            <th>Descripcio</th>
            <th>Tipus</th>
        </tr>
        <c:forEach items="${proves}" var="prova">
            <tr>
                <td>${prova.nom}</td>
                <td>${prova.descripcio}</td>
                <td>${prova.tipus}</td>
                <td><a href="update/${prova.nom}.html">Edita</a></td>
                <td><a href="delete/${prova.nom}.html">Esborra</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Afegeix prova</a>
    <br>
    <a href="../index.jsp">Torna al home</a>
</body>
</html>
