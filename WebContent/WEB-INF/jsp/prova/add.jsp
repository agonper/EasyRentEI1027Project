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
    <title>Competició Natació - Crear nova prova</title>
</head>
<body>
    <h2>Nova prova</h2>
    <form:form method="post" modelAttribute="prova">
        <table>
            <tr>
                <td><form:label path="nom">Nom</form:label></td>
                <td><form:input path="nom"/></td>
            </tr>
            <tr>
                <td><form:label path="descripcio">Descripcio</form:label></td>
                <td><form:input path="descripcio"/></td>
            </tr>
            <tr>
                <td><form:label path="tipus">Tipus</form:label></td>
                <td><form:input path="tipus"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Afegeix prova">
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
