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
    <title>Competició Natació - Modificar nadador: ${nadador.nom}</title>
</head>
<body>
    <h2>Modificar nadador: ${nadador.nom}</h2>
    <form:form method="post" modelAttribute="nadador">
        <table>
            <tr>
                <td><form:label path="nom">Nom</form:label></td>
                <td><form:input path="nom"/></td>
            </tr>
            <tr>
                <td><form:label path="numFederat">Num. Federat</form:label></td>
                <td><form:input path="numFederat"/></td>
            </tr>
            <tr>
                <td><form:label path="sexe">Sexe</form:label></td>
                <td><form:input path="sexe"/></td>
            </tr>
            <tr>
                <td><form:label path="pais">Pais</form:label></td>
                <td><form:input path="pais"/></td>
            </tr>
            <tr>
                <td><form:label path="edat">Edat</form:label></td>
                <td><form:input path="edat"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Modifica nadador">
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
