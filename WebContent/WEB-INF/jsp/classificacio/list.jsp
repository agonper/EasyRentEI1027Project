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
    <title>Gestionar Classificacions</title>
</head>
<body>
    <h1>Llista de Classificacions</h1>
    <table>
        <tr>
            <th>Nom nadador</th>
            <th>Nom prova</th>
            <th>Posicio</th>
            <th>Temps</th>
        </tr>
        <c:forEach items="${classificacions}" var="classificacio">
            <tr>
                <td>${classificacio.nomNadador}</td>
                <td>${classificacio.nomProva}</td>
                <td>${classificacio.posicio}</td>
                <td>${classificacio.temps}</td>
                <td><a href="update/${classificacio.nomNadador}/${classificacio.nomProva}.html">Edita</a></td>
                <td><a href="delete/${classificacio.nomNadador}/${classificacio.nomProva}.html">Esborra</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Afegeix classificacio</a>
    <br>
    <a href="../index.jsp">Torna al home</a>
</body>
</html>
