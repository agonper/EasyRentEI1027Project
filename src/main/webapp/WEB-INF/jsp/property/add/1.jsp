<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="coreActions" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>

<fmt:message key="add-property.title" bundle="${lang}" var="title"/>
<t:paginabasica title="${title}">
    <jsp:body>
        <div class="page-header">
            <h1>${title}</h1>
        </div>

        <div class="row bs-wizard" style="border-bottom:0;">

            <div class="col-xs-offset-1 col-xs-2 bs-wizard-step complete">
                <div class="text-center bs-wizard-stepnum"><span class="glyphicon glyphicon-user"></span></div>
                <div class="progress"><div class="progress-bar"></div></div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">Personal data</div>
            </div>

            <div class="col-xs-2 bs-wizard-step active">
                <div class="text-center bs-wizard-stepnum"><span class="glyphicon glyphicon-pencil"></span></div>
                <div class="progress"><div class="progress-bar"></div></div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">Address info</div>
            </div>

            <div class="col-xs-2 bs-wizard-step disabled">
                <div class="text-center bs-wizard-stepnum"><span class="glyphicon glyphicon-home"></span></div>
                <div class="progress"><div class="progress-bar"></div></div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">Property info</div>
            </div>

            <div class="col-xs-2 bs-wizard-step disabled">
                <div class="text-center bs-wizard-stepnum"><span class="glyphicon glyphicon-calendar"></span></div>
                <div class="progress"><div class="progress-bar"></div></div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">Availability dates</div>
            </div>

            <div class="col-xs-2 bs-wizard-step disabled">
                <div class="text-center bs-wizard-stepnum"><span class="glyphicon glyphicon-picture"></span></div>
                <div class="progress"><div class="progress-bar"></div></div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">Photos</div>
            </div>
        </div>

        <div class="panel panel-warning">
            <div class="panel-heading">
                <fmt:message key="profile.address-info" bundle="${lang}"/>
            </div>
            <div class="panel-body">
                <form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/property/add/1" method="post" modelAttribute="addressInfoForm">
                    <fmt:message key="user.address" var="address" bundle="${lang}"/>
                    <t:input path="address" required="true" label="${address}"/>
                    <fmt:message key="user.country" var="country" bundle="${lang}"/>
                    <t:input path="country" required="true" label="${country}"/>
                    <fmt:message key="user.post-code" var="postCode" bundle="${lang}"/>
                    <t:input path="postCode" required="true" label="${postCode}"/>
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