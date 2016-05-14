<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:message key="general.edit" var="edit" bundle="${lang}"/>
<fmt:message key="profile.personal-data" var="title" bundle="${lang}"/>
<sec:authentication var="loggedUser" property="principal" />
<t:paginabasica title="${edit} ${title.toLowerCase()}">
    <jsp:body>
        <h1>${edit} ${title.toLowerCase()}</h1>
        <hr>
        <form:form cssClass="form-horizontal" method="post" modelAttribute="personalDataForm">
            <fmt:message key="user.name" var="name" bundle="${lang}"/>
            <t:input path="name" label="${name}"/>
            <fmt:message key="user.surnames" var="surnames" bundle="${lang}"/>
            <t:input path="surnames" label="${surname}"/>
            <fmt:message key="user.dni" var="dni" bundle="${lang}"/>
            <t:input path="dni" label="${dni}"/>
            <fmt:message key="user.country-prefix" var="countryPrefix" bundle="${lang}"/>
            <t:input path="countryPrefix" label="${countryPrefix}"/>
            <fmt:message key="user.phone-number" var="phoneNumber" bundle="${lang}"/>
            <t:input path="phoneNumber" label="${phoneNumber}"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-save"></span> <fmt:message key="general.save" bundle="${lang}"/></button>
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/user/profile/${loggedUser.id}.html?personalData#menu"><span class="glyphicon glyphicon-backward"></span> <fmt:message key="general.back" bundle="${lang}"/> </a>
                </div>
            </div>
        </form:form>
    </jsp:body>
</t:paginabasica>