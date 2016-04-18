<%--
  Created by IntelliJ IDEA.
  User: alberto
  Date: 13/04/16
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Competició Natació - Crear nova classificacio</title>
</head>
<body>
    <h2>Nova classificacio</h2>
    <form:form method="post" modelAttribute="classificacio">
        <table>
            <tr>
                <td><form:label path="nomNadador">Nom nadador</form:label></td>
                <td><form:input path="nomNadador"/></td>
            </tr>
            <tr>
                <td><form:label path="nomProva">Nom prova</form:label></td>
                <td><form:input path="nomProva"/></td>
            </tr>
            <tr>
                <td><form:label path="posicio">Posicio</form:label></td>
                <td><form:input path="posicio"/></td>
            </tr>
            <tr>
                <td><form:label path="temps">Temps</form:label></td>
                <td><form:input path="temps"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Afegeix classificacio">
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
