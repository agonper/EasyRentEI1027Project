<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="coreActions" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="input" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="navs" tagdir="/WEB-INF/tags/navs" %>

<fmt:message key="add-property.title" bundle="${lang}" var="title"/>
<fmt:message key="property.property-info" bundle="${lang}" var="subtitle"/>
<tag:paginabasica title="${title}: ${subtitle}">
    <jsp:body>
        <tag:property-add-breadcrumb subtitle="${subtitle}"/>
        <div class="page-header">
            <h1>${title} <small>${subtitle}</small></h1>
        </div>

        <navs:stepper step="${pageContext.session.getAttribute('addPropertyMap').step.ordinal()}" steps="${steps}" path="/property/add"/>

        <div class="panel panel-warning">
            <div class="panel-heading">
                ${subtitle}
            </div>
            <div class="panel-body">
                <form:form method="post" modelAttribute="property" cssClass="form-horizontal" action="${pageContext.request.contextPath}/property/add/2">
                    <div class="row">
                        <div class="col-md-6">
                            <fmt:message key="property.title" bundle="${lang}" var="name"/>
                            <tag:input path="title" label="${name}"/>
                        </div>
                        <div class="col-md-6">
                            <fmt:message key="property.location" bundle="${lang}" var="location"/>
                            <tag:input path="location" label="${location}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <fmt:message key="property.type" bundle="${lang}" var="type"/>
                            <div class="form-group">
                                <form:label path="type" cssClass="control-label col-sm-2">${type}</form:label>
                                <div class="col-sm-10">
                                    <form:select path="type" cssClass="form-control">
                                        <form:options items="${propertyTypes}" itemValue="value" itemLabel="label"/>
                                    </form:select>
                                    <form:errors path="type" cssClass=""/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <fmt:message key="property.price-per-day" bundle="${lang}" var="pricePerDay"/>
                            <tag:input path="pricePerDay" type="number" step=".01" label="${pricePerDay}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <fmt:message key="property.capacity" bundle="${lang}" var="capacity"/>
                            <tag:input path="capacity" type="number" label="${capacity} &nbsp;(<span class='glyphicon glyphicon-user'></span>)"/>
                        </div>
                        <div class="col-md-6">
                            <fmt:message key="property.rooms" bundle="${lang}" var="rooms"/>
                            <tag:input path="rooms" type="number" label="${rooms}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <fmt:message key="property.bathrooms" bundle="${lang}" var="bathrooms"/>
                            <tag:input path="bathrooms" type="number" label="${bathrooms}"/>
                        </div>
                        <div class="col-md-6">
                            <fmt:message key="property.beds" bundle="${lang}" var="beds"/>
                            <tag:input path="beds" type="number" label="${beds}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <fmt:message key="property.floor-space" bundle="${lang}" var="floorSpace"/>
                            <tag:input path="floorSpace" type="number" label="${floorSpace}"/>
                        </div>
                        <div class="col-md-6">
                            <fmt:message key="property.description" bundle="${lang}" var="description"/>
                            <tag:input path="description" label="${description}" type="textarea"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <a href="${pageContext.request.contextPath}/property/add?step=1" class="btn btn-warning"><span class="glyphicon glyphicon-backward"></span> <fmt:message key="general.back" bundle="${lang}"/> </a>
                            <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-forward"></span> <fmt:message key="general.next" bundle="${lang}"/> </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

    </jsp:body>
</tag:paginabasica>