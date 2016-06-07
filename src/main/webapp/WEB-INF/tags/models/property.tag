<%@ attribute name="property" type="es.uji.daal.easyrent.model.Property" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
                <li data-target="#property-show-carousel" data-slide-to="0" class="active"></li>
                <li data-target="#property-show-carousel" data-slide-to="1"></li>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="${pageContext.request.contextPath}/img/neighborhood1.jpg" width="720" height="480">
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/img/neighborhood2.jpg" width="720" height="480">
                </div>
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
        ## Services ##
    </div>
</div>