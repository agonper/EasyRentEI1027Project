<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="input" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="navs" tagdir="/WEB-INF/tags/navs" %>
<%@ taglib prefix="fm" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="er" uri="/WEB-INF/easy-rent.tld" %>


<fmt:message key="edit-property.title" bundle="${lang}" var="title"/>
<tag:paginabasica title="${title}">
    <jsp:body>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong><fmt:message key="general.success" bundle="${lang}"/> </strong> <fmt:message key="edit-property.success" bundle="${lang}" />
            </div>
        </c:if>

        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/index.html"><fmt:message key="index.home" bundle="${lang}"/></a></li>
            <li><a href="${pageContext.request.contextPath}/index.html#owner-properties"><fmt:message key="home.my-properties" bundle="${lang}"/></a></li>
            <li><a href="${pageContext.request.contextPath}/property/show/${property.id}.html">${property.title}</a></li>
            <li class="active">${title}</li>
        </ol>

        <div class="page-header">
            <h1>${title} <small>${subtitle}</small></h1>
        </div>

        <ul class="nav nav-tabs nav-justified" id="user-options">
            <li role="presentation" class="active"><a data-toggle="tab" href="#edit-property"><fmt:message key="edit-property.title" bundle="${lang}"/></a></li>
            <li role="presentation"><a data-toggle="tab" href="#edit-availability-dates"><fmt:message key="edit-property.availability-dates" bundle="${lang}"/></a></li>
            <li role="presentation"><a data-toggle="tab" href="#edit-services"><fmt:message key="edit-property.services" bundle="${lang}"/></a></li>
            <li role="presentation"><a data-toggle="tab" href="#edit-photos"><fmt:message key="edit-property.photos" bundle="${lang}"/></a></li>
        </ul>
        <br>

        <div class="tab-content">
            <div id="edit-property" class="tab-pane fade in active">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <fmt:message key="property.property-info" bundle="${lang}"/>
                    </div>
                    <div class="panel-body">
                        <form:form method="post" modelAttribute="propertyForm" cssClass="form-horizontal">
                            <fm:property-info/>
                            <div class="row">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <a href="${pageContext.request.contextPath}/property/show/${property.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-backward"></span> <fmt:message key="edit-property.back" bundle="${lang}"/> </a>
                                    <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-save"></span> <fmt:message key="general.save" bundle="${lang}"/> </button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div id="edit-availability-dates" class="tab-pane fade">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <fmt:message key="property.availability-dates" bundle="${lang}"/>
                    </div>
                    <div class="panel-body">
                        <c:if test="${not empty property.availabilityPeriods}">
                            <c:forEach var="availabilityPeriod" items="${property.availabilityPeriods}" varStatus="status">
                                <form style="margin: 3px;" class="form-inline" method="post" action="${pageContext.request.contextPath}/property/availability-period/update/${availabilityPeriod.id}">
                                    <div class="form-group datepicker-container">
                                        <label><fmt:message key="property.availability-period" bundle="${lang}"/> #${status.index+1}</label>
                                        <div class="input-daterange input-group" id="range-${status.index}">
                                            <input value="<spring:eval expression="availabilityPeriod.startDate"/>" name="startDate" class="input-sm form-control" required/>
                                            <span class="input-group-addon">to</span>
                                            <c:choose>
                                                <c:when test="${empty availabilityPeriod.endDate}">
                                                    <input value="<spring:eval expression="availabilityPeriod.startDate"/>" name="endDate" class="input-sm form-control" required/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input value="<spring:eval expression="availabilityPeriod.endDate"/>" name="endDate" class="input-sm form-control" required/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label><fmt:message key="general.henceforth" bundle="${lang}"/></label>
                                        <input type="checkbox" name="endless" ${empty availabilityPeriod.endDate ? 'checked' : ''}/>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span></button>
                                        <a href="${pageContext.request.contextPath}/property/availability-period/delete/${availabilityPeriod.id}" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </c:forEach>
                            <hr>
                        </c:if>
                        <form:form cssClass="form-inline" method="post" modelAttribute="availabilityForm" action="${pageContext.request.contextPath}/property/availability-period/${property.id}/add">
                            <div class="form-group datepicker-container">
                                <label><fmt:message key="property.availability-period" bundle="${lang}"/></label>
                                <div class="input-daterange input-group" id="new-range">
                                    <form:input path="startDate" required="true" cssClass="input-sm form-control"/>
                                    <span class="input-group-addon">to</span>
                                    <form:input path="endDate" required="true" cssClass="input-sm form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="general.henceforth" bundle="${lang}"/></label>
                                <input type="checkbox" name="endless"/>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-plus"></span> <fmt:message key="general.add" bundle="${lang}"/></button>
                            </div>
                        </form:form>
                        <br>
                        <div class="row">
                            <div class="col-sm-offset-1 col-sm-10">
                                <a href="${pageContext.request.contextPath}/property/show/${property.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-backward"></span> <fmt:message key="edit-property.back" bundle="${lang}"/> </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="edit-services" class="tab-pane fade">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <fmt:message key="property.services" bundle="${lang}"/>
                    </div>
                    <div class="panel-body">

                    </div>
                </div>
            </div>
            <div id="edit-photos" class="tab-pane fade">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <fmt:message key="property.photos" bundle="${lang}"/>
                    </div>
                    <div class="panel-body">

                    </div>
                </div>
            </div>
        </div>
        <script>
            (function () {
                $(document).ready(function () {
                    var fragment = document.location.hash;
                    if (fragment != "") {
                        $('a[href="' + fragment + '"]').tab('show');
                    }
                });

                $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
                    var $toBeShown = $(e.target);
                    $toBeShown.parent().find('.active').removeClass('active');
                    $toBeShown.addClass('active');
                });

                <er:availabilities-to-json periods="${property.availabilityPeriods}" var="jsonPeriods"/>
                var availabilityPeriods = JSON.parse('${jsonPeriods}');

                <er:time-config type="datepicker" var="datepickerFormat"/>
                $('.datepicker-container').find('.input-daterange').each(function (_, el) {
                    var $el = $(el);
                    var id = $el.attr("id");
                    var idParts = id.split('-');
                    id = idParts[idParts.length-1];

                    $el.datepicker({
                        format: '${datepickerFormat}',
                        beforeShowDay: function (date) {
                            var momentDate = moment(date);
                            if (momentDate.isBefore(moment())) {
                                return false;
                            }
                            var enabled = true;
                            availabilityPeriods.forEach(function (period, index) {
                                if (id === String(index)) {
                                    return;
                                }
                                var startDate = moment(period.startDate);
                                if (period.endless) {
                                    if (momentDate.isAfter(startDate) || momentDate.isSame(startDate, 'day')) {
                                        enabled = false;
                                        return;
                                    }
                                } else {
                                    var endDate = moment(period.endDate);
                                    var range = moment().range(startDate, endDate);
                                    if (range.contains(momentDate)) {
                                        enabled = false;
                                        return;
                                    }
                                }
                            });
                            return enabled;
                        }
                    });
                });
            })();
        </script>
    </jsp:body>
</tag:paginabasica>