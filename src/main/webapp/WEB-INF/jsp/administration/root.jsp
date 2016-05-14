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
        <span class="h1">${title}</span>
            <div class="container">
                <div class="row">
                    <hr>
                    <div class="col-md-12">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#users">Users management</a></li>
                            <li><a data-toggle="tab" href="#properties">Properties management</a></li>
                            <li><a data-toggle="tab" href="#booking_proposals">Booking proposals management</a></li>
                            <li><a data-toggle="tab" href="#invoices">Invoices control</a></li>
                        </ul>

                        <div class="tab-content">
                            <div id="users" class="tab-pane fade in active">
                                <div class="panel panel-warning top-padding">
                                    <div class="panel-heading">Search for users</div>
                                    <div class="panel-body">
                                        <form:form cssClass="form-horizontal" method="post" action="/searchUsers" modelAttribute="user">
                                            <div class="form-group">
                                                <input type="text">

                                                <button type="submit" class="btn btn-warning">
                                                    <fmt:message key="general.search" bundle="${lang}"/>
                                                </button>
                                            </div>
                                        </form:form>
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
                                    </div>
                                </div>
                            </div>
                            <div id="properties" class="tab-pane fade">

                            </div>
                            <div id="booking_proposals" class="tab-pane fade">

                            </div>
                            <div id="invoices" class="tab-pane fade">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </jsp:body>
</t:paginabasica>