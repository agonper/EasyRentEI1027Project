<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:paginabasica title="Login">
    <form:form method="post" modelAttribute="user"
               action="${pageContext.request.contextPath}/login.html" cssClass="form-group">
        <p>
            <form:label path="username">Username:</form:label>
            <form:input path="username" cssClass="form-control"/>
            <form:errors path="username" cssClass="error" />
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password" cssClass="form-control"/>
            <form:errors path="password" cssClass="error" />
        </p>
        <input type="submit" value="Access" class="btn btn-default"/>
    </form:form>
</t:paginabasica>
