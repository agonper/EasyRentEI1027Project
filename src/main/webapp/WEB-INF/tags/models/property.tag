<%@ attribute name="property" type="es.uji.daal.easyrent.model.Property" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="availabilityPeriods" type="java.util.List<es.uji.daal.easyrent.view_models.AvailabilityForm>" %>
<%@ attribute name="services" type="java.util.List<es.uji.daal.easyrent.model.Service>" %>
<%@ attribute name="photos" type="java.util.List<es.uji.daal.easyrent.model.Photo>" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="er" uri="/WEB-INF/easy-rent.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <div id="property-show-carousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <c:choose>
                    <c:when test="${not empty photos or not empty property.photos}">
                        <c:if test="${empty photos}">
                            <c:set var="photos" value="${property.photos}"/>
                        </c:if>
                        <c:forEach var="photo" items="${photos}" varStatus="status">
                            <li data-target="#property-show-carousel" data-slide-to="${status.index}" ${status.index eq 0 ? 'class="active"' : ''}></li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li data-target="#property-show-carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#property-show-carousel" data-slide-to="1"></li>
                    </c:otherwise>
                </c:choose>
            </ol>

            <div class="carousel-inner" role="listbox">
                <c:choose>
                    <c:when test="${not empty photos or not empty property.photos}">
                        <c:if test="${empty photos}">
                            <c:set var="photos" value="${property.photos}"/>
                        </c:if>
                        <c:forEach var="photo" items="${photos}" varStatus="status">
                            <div class="item ${status.index eq 0 ? 'active' : ''}">
                                <img src="${pageContext.request.contextPath}/uploads/property-pics/${photo.filename}" width="720" height="480">
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="item active">
                            <img src="${pageContext.request.contextPath}/img/neighborhood1.jpg" width="720" height="480">
                        </div>
                        <div class="item">
                            <img src="${pageContext.request.contextPath}/img/neighborhood2.jpg" width="720" height="480">
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <a class="left carousel-control" href="#property-show-carousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#property-show-carousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
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
        <fmt:message key="property.characteristics" bundle="${lang}"/>
    </div>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message key="property.type" bundle="${lang}"/></th>
                <th><fmt:message key="property.rooms" bundle="${lang}"/></th>
                <th><fmt:message key="property.beds" bundle="${lang}"/></th>
                <th><fmt:message key="property.bathrooms" bundle="${lang}"/></th>
                <th><fmt:message key="property.floor-space" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${property.type.label}</td>
                <td>${property.rooms}</td>
                <td>${property.beds}</td>
                <td>${property.bathrooms}</td>
                <td>${property.floorSpace}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="panel panel-warning">
    <div class="panel-heading">
        <fmt:message key="property.services" bundle="${lang}"/>
    </div>
    <div class="panel-body">
        <c:choose>
            <c:when test="${not empty services or not empty property.services}">
                <c:if test="${empty services}">
                    <c:set var="services" value="${property.services}"/>
                </c:if>
                <c:forEach var="service" items="${services}" varStatus="status">
                    <er:color-gen number="${status.index}" var="color"/>
                    <span class="h3">
                        <span style="margin: 5px" class="label label-${color}">
                                ${service.name}
                        </span>
                    </span>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <fmt:message key="service.none" bundle="${lang}"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<er:time-config type="datepicker" var="datepickerFormat"/>
<c:choose>
    <c:when test="${availabilityPeriods ne null}">
        <er:object-to-json object="${availabilityPeriods}" var="jsonPeriods"/>
    </c:when>
    <c:otherwise>
        <er:availabilities-to-json periods="${property.availabilityPeriods}" var="jsonPeriods"/>
    </c:otherwise>
</c:choose>
<script>
    (function () {
        var availabilityPeriods = JSON.parse('${jsonPeriods}');

        $('#availability-calendar').datepicker({
            format: '${datepickerFormat}',
            beforeShowDay: function (date) {
                var momentDate = moment(date);
                var options = {
                    enabled: false,
                    classes: ""
                };
                if (momentDate.isBefore(moment())) {
                    return options;
                }
                availabilityPeriods.forEach(function (period) {
                    var startDate = moment(period.startDate);
                    if (period.endless) {
                        if (momentDate.isAfter(startDate) || momentDate.isSame(startDate, 'day')) {
                            options.enabled = true;
                            options.classes = "active disabled";
                            return;
                        }
                    } else {
                        var endDate = moment(period.endDate);
                        var range = moment().range(startDate, endDate);
                        if (range.contains(momentDate)) {
                            options.enabled = true;
                            options.classes = "active disabled";
                            return;
                        }
                    }
                });
                return options;
            }
        });
    })();
</script>