<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:message key="list-user.title" var="title" bundle="${lang}"/>
<t:paginabasica title="${title}">
    <jsp:body>
        <span class="h1">${title}</span>
        <a class="btn btn-warning" href="${pageContext.request.contextPath}/user/add.html">
            <span class="glyphicon glyphicon-plus"></span> <fmt:message key="add-user.title" bundle="${lang}"/>
        </a>
        <hr>
        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="user.username" bundle="${lang}"/></th>
                    <th><fmt:message key="user.dni" bundle="${lang}"/></th>
                    <th><fmt:message key="user.role" bundle="${lang}"/></th>
                    <th><fmt:message key="user.password" bundle="${lang}"/></th>
                    <th><fmt:message key="user.name" bundle="${lang}"/></th>
                    <th><fmt:message key="user.surnames" bundle="${lang}"/></th>
                    <th><fmt:message key="user.email" bundle="${lang}"/></th>
                    <th><fmt:message key="user.phone-number" bundle="${lang}"/></th>
                    <th><fmt:message key="user.address" bundle="${lang}"/></th>
                    <th><fmt:message key="user.country" bundle="${lang}"/></th>
                    <th><fmt:message key="user.post-code" bundle="${lang}"/></th>
                    <th><fmt:message key="user.signup-date" bundle="${lang}"/></th>
                    <th><fmt:message key="user.active" bundle="${lang}"/></th>
                    <th><fmt:message key="user.deactivated-since" bundle="${lang}"/></th>
                    <th><fmt:message key="general.edit" bundle="${lang}"/></th>
                    <th><fmt:message key="general.delete" bundle="${lang}"/></th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.dni}</td>
                        <td>${user.role}</td>
                        <td>${user.password}</td>
                        <td>${user.name}</td>
                        <td>${user.surnames}</td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.postalAddress}</td>
                        <td>${user.country}</td>
                        <td>${user.postCode}</td>
                        <td>${user.signUpDate}</td>
                        <td>${user.active}</td>
                        <td>${user.deactivatedSince}</td>
                        <td><a href="${pageContext.request.contextPath}/user/update/${user.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span></a></td>
                        <td><a href="${pageContext.request.contextPath}/user/delete/${user.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:body>
</t:paginabasica>