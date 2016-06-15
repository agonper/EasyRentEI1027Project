<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fm" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="navs" tagdir="/WEB-INF/tags/navs" %>
<%@ taglib prefix="er" uri="/WEB-INF/easy-rent.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:message key="book-property.title" bundle="${lang}" var="title"/>
<fmt:message key="proposal.title" bundle="${lang}" var="subtitle"/>
<t:paginabasica title="${title}: ${subtitle}">
    <jsp:body>
        <t:property-add-breadcrumb subtitle="${subtitle}"/>
        <div class="page-header">
            <h1>${title} <small>${subtitle}</small></h1>
        </div>

        <navs:stepper step="${pageContext.session.getAttribute(sessionMapName).step.ordinal()}" steps="${steps}" path="/property/booking-proposal/${property.id.toString()}"/>

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
                    <c:choose>
                        <c:when test="${empty property.photos}">
                            <img class="img-responsive" src="${pageContext.request.contextPath}/img/neighborhood1.jpg" width="720" height="480">
                        </c:when>
                        <c:otherwise>
                            <img class="img-responsive" src="${pageContext.request.contextPath}/uploads/property-pics/${property.photos.toArray()[0].filename}" width="720" height="480">
                        </c:otherwise>
                    </c:choose>
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
                        <div id="availability-calendar"></div>
                    </div>
                </div>
                <div class="well">
                    <div class="row">
                        <div class="col-lg-6">
                            <strong><fmt:message key="property.price-per-day" bundle="${lang}"/> (<span class="glyphicon glyphicon-calendar"></span>)</strong>
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
                <form:form cssClass="form-horizontal" method="post" action="${pageContext.request.contextPath}/property/booking-proposal/${property.id}/2" modelAttribute="bookingForm">
                    <div class="form-group" id="datepicker-container">
                        <label class="control-label col-sm-2"><fmt:message key="proposal.date-range" bundle="${lang}"/></label>
                        <div class="col-sm-10 col-md-6">
                            <div class="input-daterange input-group" id="datepicker">
                                <form:input path="startDate" required="true" cssClass="input-sm form-control"/>
                                <span class="input-group-addon">to</span>
                                <form:input path="endDate" required="true" cssClass="input-sm form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <form:errors cssClass="text-danger" path="startDate"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label cssClass="control-label col-sm-2" path="numberOfTenants"><fmt:message key="proposal.number-of-tenants" bundle="${lang}"/> </form:label>
                        <div class="col-sm-2">
                            <form:select path="numberOfTenants" cssClass="form-control">
                                <c:forEach begin="1" end="${property.capacity}" var="count">
                                    <form:option value="${count}" label="${count}"/>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <a href="${pageContext.request.contextPath}/property/booking-proposal/${property.id}.html?step=1" class="btn btn-warning"><span class="glyphicon glyphicon-backward"></span> <fmt:message key="general.back" bundle="${lang}"/></a>
                            <button type="submit" class="btn btn-warning"><fmt:message key="general.next" bundle="${lang}"/> <span class="glyphicon glyphicon-forward"></span></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <er:time-config type="datepicker" var="datepickerFormat"/>
        <er:time-config type="moment" var="momentFormat"/>
        <er:availabilities-to-json periods="${property.availabilityPeriods}" var="jsonPeriods"/>
        <script>
            (function () {
                var availabilityPeriods = JSON.parse('${jsonPeriods}');

                $('#datepicker-container').find('.input-daterange').datepicker({
                    format: '${datepickerFormat}',
                    beforeShowDay: function (date) {
                        return beforeShowDay(date, "")
                    }
                });

                $('#availability-calendar').datepicker({
                    format: '${datepickerFormat}',
                    beforeShowDay: function (date) {
                        return beforeShowDay(date, "active disabled");
                    }
                });

                function beforeShowDay(date, classes) {
                    var momentDate = moment(date);
                    var options = {
                        enabled: false,
                        classes: ""
                    };
                    availabilityPeriods.forEach(function (period) {
                        var startDate = moment(period.startDate);
                        if (period.endless) {
                            if (momentDate.isAfter(startDate) || momentDate.isSame(startDate, 'day')) {
                                options.enabled = true;
                                options.classes = classes;
                                return;
                            }
                        } else {
                            var endDate = moment(period.endDate);
                            var range = moment().range(startDate, endDate);
                            if (range.contains(momentDate)) {
                                options.enabled = true;
                                options.classes = classes;
                                return;
                            }
                        }
                    });
                    return options;
                }
            })();
        </script>
    </jsp:body>
</t:paginabasica>