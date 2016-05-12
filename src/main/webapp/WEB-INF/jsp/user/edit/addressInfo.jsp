<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:message key="general.edit" var="edit" bundle="${lang}"/>
<fmt:message key="profile.personal-data" var="title" bundle="${lang}"/>
<t:paginabasica title="${edit} ${title.toLowerCase()}">
    <jsp:body>
        <h1>${edit} ${title.toLowerCase()}</h1>
        <hr>
        <form:form cssClass="form-horizontal" method="post" modelAttribute="addressInfoForm">
            <fmt:message key="user.address" var="address" bundle="${lang}"/>
            <t:input path="address" required="true" label="${address}"/>
            <fmt:message key="user.country" var="country" bundle="${lang}"/>
            <t:input path="country" required="true" label="${country}"/>
            <fmt:message key="user.post-code" var="postCode" bundle="${lang}"/>
            <t:input path="postCode" required="true" label="${postCode}"/>
            <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-save"></span> <fmt:message key="general.save" bundle="${lang}"/></button>
        </form:form>
    </jsp:body>
</t:paginabasica>