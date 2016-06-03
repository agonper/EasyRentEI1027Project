<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fm" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="navs" tagdir="/WEB-INF/tags/navs" %>

<fmt:message key="add-property.title" bundle="${lang}" var="title"/>
<t:paginabasica title="${title}">
    <jsp:body>
        <div class="page-header">
            <h1>${title}</h1>
        </div>

        <navs:add-property step="${pageContext.session.getAttribute('addPropertyMap').step.ordinal()}" steps="${steps}" path="/property/add"/>

        <div class="panel panel-warning">
            <div class="panel-heading">
                <fmt:message key="profile.address-info" bundle="${lang}"/>
            </div>
            <div class="panel-body">
                <form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/property/add/1" method="post" modelAttribute="addressInfoForm">
                    <fm:address-info/>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <a href="${pageContext.request.contextPath}/property/add.html?step=0" class="btn btn-warning"><span class="glyphicon glyphicon-backward"></span> <fmt:message key="general.back" bundle="${lang}"/></a>
                            <button type="submit" class="btn btn-warning"><fmt:message key="general.next" bundle="${lang}"/> <span class="glyphicon glyphicon-forward"></span></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>