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
    <title>Gestionar Nadadors</title>
</head>
<body>
    <h1>Llista de Nadadors</h1>
    <table>
        <tr>
            <th>NumFederat</th>
            <th>Nom</th>
            <th>Edat</th>
            <th>Pais</th>
            <th>Sexe</th>
        </tr>
        <c:forEach items="${nadadors}" var="nadador">
            <tr>
                <td>${nadador.numFederat}</td>
                <td>${nadador.nom}</td>
                <td>${nadador.edat}</td>
                <td>${nadador.pais}</td>
                <td>${nadador.sexe}</td>
                <td><a href="update/${nadador.nom}.html">Edita</a></td>
                <td><a href="delete/${nadador.nom}.html">Esborra</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Afegeix nadador</a>
    <br>
    <a href="../index.jsp">Torna al home</a>
</body>
</html>
