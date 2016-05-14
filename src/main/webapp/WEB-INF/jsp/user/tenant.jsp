<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:message key="tenant.title" var="title" bundle="${lang}"/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<t:paginabasica title="${title}: ${user.username}">
    <jsp:body>
        <span class="h1">${title}: ${user.username}</span>
        <hr>
        <t:user-options user="${user}" location="tenant"/>
        <div class="row">
            <div class="col-md-3">
                <div class="panel panel-warning" id="sub-menu">
                    <div class="panel-heading">
                        <fmt:message key="tenant.sections" bundle="${lang}"/>
                    </div>
                    <div class="list-group">
                        <a class="list-group-item ${(param.size() == 0 or param.proposals != null) ? 'active' : ''}" href="?proposals#sub-menu">
                            <fmt:message key="tenant.emited-proposals" bundle="${lang}"/>
                        </a>
                        <a class="list-group-item ${(param.invoices != null) ? 'active' : ''}" href="?invoices#sub-menu">
                            <fmt:message key="tenant.invoices" bundle="${lang}"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div class="panel panel-warning">
                    <c:if test="${param.size() == 0 or param.proposals != null}">
                        <div class="panel-heading">
                            <fmt:message key="tenant.emited-proposals" bundle="${lang}"/>
                        </div>
                        <ul class="list-group">
                            <li class="list-group-item">Stuff here...</li>
                        </ul>
                        <div class="panel-footer">
                            Footer
                        </div>
                    </c:if>
                    <c:if test="${param.invoices != null}">
                        <div class="panel-heading">
                            <fmt:message key="tenant.invoices" bundle="${lang}"/>
                        </div>
                        <ul class="list-group">
                            <li class="list-group-item">Stuff here...</li>
                        </ul>
                        <div class="panel-footer">
                            Footer
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>