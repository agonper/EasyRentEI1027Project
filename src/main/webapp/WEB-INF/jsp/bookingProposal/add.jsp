<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="proposal.title" bundle="${lang}" var="title"/>
<t:paginabasica title="${title}">
    <jsp:body>
        <div class="page-header">
            <span class="h1">${title} <fmt:message key="general.for" bundle="${lang}"/>: <a href="${pageContext.request.contextPath}/property/show/${property.id}.html">${property.title}</a></span>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">
                <fmt:message key="proposal.help-title" bundle="${lang}"/>
            </div>
            <div class="panel-body">
                <%-- TODO fullfil this --%>
                ## INCLUDE SOME TIPS HERE ##
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4">
                <span class="glyphicon glyphicon-map-marker"></span> ${property.location}
            </div>
            <div class="col-sm-4">
                <strong><fmt:message key="general.max" bundle="${lang}"/></strong> <span class="glyphicon glyphicon-user"></span> <span class="badge">${property.capacity}</span>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-md-8">
                <%-- TODO Move this to a tag once we can upload images--%>
                <img class="img-responsive" src="${pageContext.request.contextPath}/img/neighborhood1.jpg" width="720" height="480">
            </div>
            <div class="col-md-4">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <fmt:message key="property.description" bundle="${lang}"/>
                    </div>
                    <div class="panel-body">
                            ${property.description}
                    </div>
                    <div class="panel-heading">
                        <fmt:message key="property.availability-dates" bundle="${lang}"/>
                    </div>
                    <div class="panel-body">
                        ## Dates ##
                    </div>
                </div>
                <div class="well">
                    <div class="row">
                        <div class="col-lg-6">
                            <strong><fmt:message key="property.price" bundle="${lang}"/> (<span class="glyphicon glyphicon-calendar"></span> / <span class="glyphicon glyphicon-user"></span>)</strong>
                        </div>
                        <div class="col-lg-6">
                            <strong><span class="h2"><t:show-price amount="${property.pricePerDay}"/></span></strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="panel panel-warning">
            <div class="panel-heading">
                ${title}
            </div>
            <div class="panel-body">
                <form:form cssClass="form-inline" method="post" modelAttribute="bookingForm">
                    <div class="form-group" id="datepicker-container">
                        <label><fmt:message key="proposal.date-range" bundle="${lang}"/></label>
                        <div class="input-daterange input-group" id="datepicker">
                            <form:input path="startDate" cssClass="input-sm form-control"/>
                            <span class="input-group-addon">to</span>
                            <form:input path="endDate" cssClass="input-sm form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="numberOfTenants"><fmt:message key="proposal.number-of-tenants" bundle="${lang}"/> </form:label>
                        <form:select path="numberOfTenants" cssClass="form-control">
                            <c:forEach begin="1" end="${property.capacity}" var="count">
                                <form:option value="${count}" label="${count}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-check"></span> <fmt:message key="proposal.book" bundle="${lang}"/> </button>
                    </div>
                    <form:errors cssClass="text-danger" path="startDate"/>
                    <form:errors cssClass="text-danger" path="endDate"/>
                </form:form>
            </div>
        </div>
        <script>
            $('#datepicker-container').find('.input-daterange').datepicker({
                format: "dd/mm/yyyy"
            });
        </script>
    </jsp:body>
</t:paginabasica>