<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:message key="signup.title" var="title" bundle="${lang}"/>
<t:paginabasica title="${title}">
    <jsp:body>
        <h1>${title}</h1>
        ${message}
        <form:form cssClass="form-horizontal" method="post" modelAttribute="form">
            <fmt:message key="user.username" var="username" bundle="${lang}"/>
            <t:input path="username" label="${username}"/>
            <fmt:message key="user.password" var="password" bundle="${lang}"/>
            <t:input path="password" type="password" label="${password}"/>
            <fmt:message key="signup.repeat-password" var="repeatPassword" bundle="${lang}"/>
            <t:input path="repeatPassword" type="password" label="${repeatPassword}"/>
            <fmt:message key="user.email" var="email" bundle="${lang}"/>
            <t:input path="email" label="${email}"/>
            <fmt:message key="signup.repeat-email" var="repeatEmail" bundle="${lang}"/>
            <t:input path="repeatEmail" label="${repeatEmail}"/>
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> <fmt:message key="general.add" bundle="${lang}"/></button>
        </form:form>
        <%--<form:form cssClass="form-horizontal" method="post" modelAttribute="user">
            <fmt:message key="user.username" var="username" bundle="${lang}"/>
            <t:input path="username" label="${username}"/>
            <fmt:message key="user.password" var="password" bundle="${lang}"/>
            <t:input path="password" type="password" label="${password}"/>
            <fmt:message key="signup.repeat-password" var="rPassword" bundle="${lang}"/>
            <t:input path="repeatPassword" type="password" label="${rPassword}"/>
            <fmt:message key="user.email" var="email" bundle="${lang}"/>
            <t:input path="email" label="${email}"/>
            <fmt:message key="signup.repeat-email" var="rEmail" bundle="${lang}"/>
            <t:input path="repeatEmail" label="${rEmail}"/>
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> <fmt:message key="general.add" bundle="${lang}"/></button>
        </form:form>--%>
    </jsp:body>
</t:paginabasica>