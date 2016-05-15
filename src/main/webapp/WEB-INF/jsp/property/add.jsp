<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="coreActions" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="input" tagdir="/WEB-INF/tags" %>

<fmt:message key="add-property.title" bundle="${lang}" var="title"/>
<tag:paginabasica title="${title}">
    <jsp:body>
        <div class="page-header">
            <h1>${title}</h1>
        </div>
        <form:form method="post" modelAttribute="property" cssClass="form-horizontal">
            <fmt:message key="property.title" bundle="${lang}" var="name"/>
            <tag:input path="title" label="${name}"/>
            <fmt:message key="property.location" bundle="${lang}" var="location"/>
            <tag:input path="location" label="${location}"/>
            <fmt:message key="property.rooms" bundle="${lang}" var="rooms"/>
            <tag:input path="rooms" label="${rooms}"/>
            <fmt:message key="property.capacity" bundle="${lang}" var="capacity"/>
            <tag:input path="capacity" label="${capacity} (<span class='glyphicon glyphicon-user'></span>)"/>
            <fmt:message key="property.beds" bundle="${lang}" var="beds"/>
            <tag:input path="beds" label="${beds}"/>
            <fmt:message key="property.bathrooms" bundle="${lang}" var="bathrooms"/>
            <tag:input path="bathrooms" label="${bathrooms}"/>
            <fmt:message key="property.floor-space" bundle="${lang}" var="floorSpace"/>
            <tag:input path="floorSpace" label="${floorSpace}"/>
            <fmt:message key="property.price-per-day" bundle="${lang}" var="pricePerDay"/>
            <tag:input path="pricePerDay" label="${pricePerDay}"/>
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
            <fmt:message key="property.description" bundle="${lang}" var="description"/>
            <tag:input path="description" label="${description}" type="textarea"/>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-plus"></span> <fmt:message key="add-property.add" bundle="${lang}"/> </button>
                </div>
            </div>
        </form:form>

    </jsp:body>
</tag:paginabasica>