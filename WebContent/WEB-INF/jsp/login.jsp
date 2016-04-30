<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags/form" %>

<t:paginabasica title="Login">
    <form:form method="post" modelAttribute="user" action="${pageContext.request.contextPath}/login.html" cssClass="form-group">

        <t:input path="username" label="Username" type="text"/>

        <t:input path="password" label="Password" type="password"/>

        <input type="submit" value="Access" class="btn btn-default"/>
    </form:form>
</t:paginabasica>
