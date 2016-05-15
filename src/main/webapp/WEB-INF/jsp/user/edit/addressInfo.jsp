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
        <div class="page-header">
            <h1>${edit} ${title.toLowerCase()}</h1>
        </div>
        <form:form cssClass="form-horizontal" method="post" modelAttribute="addressInfoForm">
            <fmt:message key="user.address" var="address" bundle="${lang}"/>
            <t:input path="address" required="true" label="${address}"/>
            <fmt:message key="user.country" var="country" bundle="${lang}"/>
            <t:input path="country" required="true" label="${country}"/>
            <fmt:message key="user.post-code" var="postCode" bundle="${lang}"/>
            <t:input path="postCode" required="true" label="${postCode}"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-save"></span> <fmt:message key="general.save" bundle="${lang}"/></button>
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/user/profile/${loggedUser.id}.html?addressInfo#menu"><span class="glyphicon glyphicon-backward"></span> <fmt:message key="general.back" bundle="${lang}"/> </a>
                </div>
            </div>
        </form:form>
    </jsp:body>
</t:paginabasica>