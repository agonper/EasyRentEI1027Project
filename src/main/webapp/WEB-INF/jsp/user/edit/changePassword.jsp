<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:message key="general.edit" var="edit" bundle="${lang}"/>
<fmt:message key="profile.change-password" var="title" bundle="${lang}"/>
<t:paginabasica title="${edit} ${title.toLowerCase()}">
    <jsp:body>
        <h1>${edit} ${title.toLowerCase()}</h1>
        <hr>
        <form:form cssClass="form-horizontal" method="post" modelAttribute="changePasswordForm">
            <fmt:message key="user.old-password" var="oldPassword" bundle="${lang}"/>
            <t:input path="oldPassword" type="password" label="${oldPassword}"/>
            <fmt:message key="user.new-password" var="newPassword" bundle="${lang}"/>
            <t:input path="newPassword" type="password" label="${newPassword}"/>
            <fmt:message key="signup.repeat-password" var="rPassword" bundle="${lang}"/>
            <t:input path="repeatPassword" type="password" label="${rPassword}"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-save"></span> <fmt:message key="general.save" bundle="${lang}"/></button>
                </div>
            </div>
        </form:form>
    </jsp:body>
</t:paginabasica>