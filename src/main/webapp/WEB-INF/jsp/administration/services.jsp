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
            <t:administration-options location="services"/>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-warning top-padding30px">
                        <div class="panel-heading">Alerts for most demanded services</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Value</th>
                                        <th>User ID</th>
                                        <th>Active</th>
                                        <th>Creation date</th>
                                        <th>Active since</th>
                                        <th>Service proposals</th>
                                    </tr>
                                    <c:forEach var="service" items="${services}">
                                        <tr>
                                            <td>${service.id}</td>
                                            <td>${service.name}</td>
                                            <td>${service.value}</td>
                                            <td>${service.user.id}</td>
                                            <td>${service.active}</td>
                                            <td>${service.creationDate}</td>
                                            <td>${service.activeSince}</td>
                                            <td>${service.serviceProposals}</td>
                                            <td><a href="${pageContext.request.contextPath}/service/changeState/${service.id}.html" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span></a> </td>
                                            <td><a href="${pageContext.request.contextPath}/service/update/${service.id}.html" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a></td>
                                            <td><a href="${pageContext.request.contextPath}/service/delete/${service.id}.html" class="btn btn-primary"><span class="glyphicon glyphicon-remove"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                        <div class="panel-heading">Search for services</div>
                        <div class="panel-body">

                            <form class="form-inline" method="get" action="/searchServices">
                                <div class="input-group">
                                    <input class="form-control" name="servicesAttribute" placeholder="Search for services" value="" size="80">
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

                        <div class="panel-heading">List of searched services</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Value</th>
                                        <th>User ID</th>
                                        <th>Active</th>
                                        <th>Creation date</th>
                                        <th>Active since</th>
                                        <th>Service proposals</th>
                                    </tr>
                                    <c:forEach var="service" items="${services}">
                                        <tr>
                                            <td>${service.id}</td>
                                            <td>${service.name}</td>
                                            <td>${service.value}</td>
                                            <td>${service.user.id}</td>
                                            <td>${service.active}</td>
                                            <td>${service.creationDate}</td>
                                            <td>${service.activeSince}</td>
                                            <td>${service.serviceProposals}</td>
                                            <td><a href="${pageContext.request.contextPath}/service/changeState/${service.id}.html" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span></a> </td>
                                            <td><a href="${pageContext.request.contextPath}/service/update/${service.id}.html" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a></td>
                                            <td><a href="${pageContext.request.contextPath}/service/delete/${service.id}.html" class="btn btn-primary"><span class="glyphicon glyphicon-remove"></span></a></td>
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