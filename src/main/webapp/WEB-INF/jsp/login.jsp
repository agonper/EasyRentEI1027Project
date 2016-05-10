<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags/form" %>

<c:url value="/login.html" var="loginUrl"/>
<t:paginabasica title="Login">
    <form:form method="post" action="${loginUrl}" cssClass="form-horizontal">
        <c:if test="${param.error != null}">
            <p class="alert alert-danger">
                <span class="glyphicon glyphicon-exclamation-sign"></span> Invalid username or password.
            </p>
        </c:if>

        <c:if test="${param.logout != null}">
            <p class="alert alert-success">
                <span class="glyphicon glyphicon-check"></span> You have been logged out.
            </p>
        </c:if>

        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
                <input class="form-control" id="username" name="username" placeholder="Username">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Log in</button>
            </div>
        </div>
    </form:form>
</t:paginabasica>
