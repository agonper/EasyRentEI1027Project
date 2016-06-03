<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:message key="administration.title" var="title" bundle="${lang}"/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<t:paginabasica title="${title}">
    <jsp:body>
        <div class="page-header">
            <span class="h1">${title}</span>
        </div>
        <div class="container">
            <hr>
            <t:administration-options location="users"/>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-warning top-padding30px">
                        <div class="panel-heading">Search for users</div>
                        <div class="panel-body">

                            <form class="form-inline" method="get" action="/searchUsers">
                                <div class="input-group">
                                    <input class="form-control" name="userAttribute" placeholder="Search for users" value="" size="80">
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-warning">Search </button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="selectServiceAttribute" class="left-padding30px">
                                        <fmt:message key="search.by-attribute" bundle="${lang}"/>
                                    </label>
                                    <select id="selectServiceAttribute" class="form-control">
                                        <option>-</option>
                                    </select>
                                </div>
                            </form>

                        </div>

                        <div class="panel-heading">List of searched users</div>
                        <div class="panel-body">
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

                                    <c:forEach var="loggedUser" items="${users}">
                                        <tr>
                                            <td>${loggedUser.id}</td>
                                            <td>${loggedUser.username}</td>
                                            <td>${loggedUser.dni}</td>
                                            <td>${loggedUser.role}</td>
                                            <td>${loggedUser.password}</td>
                                            <td>${loggedUser.name}</td>
                                            <td>${loggedUser.surnames}</td>
                                            <td>${loggedUser.email}</td>
                                            <td>${loggedUser.phoneNumber}</td>
                                            <td>${loggedUser.postalAddress}</td>
                                            <td>${loggedUser.country}</td>
                                            <td>${loggedUser.postCode}</td>
                                            <td>${loggedUser.signUpDate}</td>
                                            <td>${loggedUser.active}</td>
                                            <td>${loggedUser.deactivatedSince}</td>
                                            <td><a href="${pageContext.request.contextPath}/user/update/${loggedUser.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span></a></td>
                                            <td><a href="${pageContext.request.contextPath}/user/delete/${loggedUser.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>