<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="er" uri="/WEB-INF/easy-rent.tld" %>

<tag:paginabasica title="EasyRent">
    <jsp:body>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/index.html"><fmt:message key="index.home" bundle="${lang}"/></a></li>
            <li class="active"><fmt:message key="general.search" bundle="${lang}"/></li>
        </ol>
        <div class="page-header">
            <h1><fmt:message key="search.title" bundle="${lang}"/></h1>
        </div>
        <div class="table-responsive">
            <c:choose>
                <c:when test="${empty properties}">
                    <div class="text-silver text-center">
                        <h3><fmt:message key="search.no-results" bundle="${lang}"/> '${param.q}'. <fmt:message key="search.less-keywords" bundle="${lang}"/> </h3>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="property" items="${properties}">
                        <div class="media">
                            <div class="media-left">
                                <a href="${pageContext.request.contextPath}/property/show/${property.id}.html?q=${param.q}">
                                    <c:choose>
                                        <c:when test="${empty property.photos}">
                                            <img class="media-object" src="${pageContext.request.contextPath}/img/neighborhood1.jpg" width="128">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="media-object" src="${pageContext.request.contextPath}/uploads/property-pics/${property.photos.toArray()[0].filename}" width="128">
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                            <div class="media-body">
                                <a href="${pageContext.request.contextPath}/property/show/${property.id}.html?q=${param.q}"><h4 class="media-heading"><c:out value="${property.title}"/></h4></a>
                                <div class="row">
                                    <div class="col-sm-10">
                                        <p><c:out value="${property.description}"/></p>
                                    </div>
                                    <div class="col-md-2 hidden-xs">
                                        <er:calculate-vat value="${property.pricePerDay}" var="priceWithVat"/>
                                        <div class="text-right">
                                            <strong><span class="h4"><t:show-price amount="${priceWithVat}"/></span></strong><br>
                                            <span class="text-silver"><small><fmt:message key="property.price-per-day" bundle="${lang}"/></small></span><br>
                                            <span class="text-silver"><small><small><fmt:message key="property.includes-vat-s" bundle="${lang}"/></small></small></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <strong><span class="glyphicon glyphicon-map-marker"></span></strong> <c:out value="${property.location}"/>
                                    </div>
                                    <div class="col-md-2">
                                        <strong><fmt:message key="property.capacity" bundle="${lang}"/></strong> <span class="glyphicon glyphicon-user"></span> <span class="badge">${property.capacity}</span>
                                    </div>
                                    <div class="col-md-1">
                                        <strong><span class="hidden-lg hidden-md"><fmt:message key="property.type" bundle="${lang}"/>:</span> ${property.type.label}</strong>
                                    </div>
                                    <div class="col-md-1">
                                        ${property.floorSpace} <fmt:message key="property.floor-space" bundle="${lang}"/>
                                    </div>
                                </div>
                                <div style="padding: 10px" class="visible-xs">
                                    <er:calculate-vat value="${property.pricePerDay}" var="priceWithVat"/>
                                    <div class="text-right">
                                        <strong><span class="h4"><t:show-price amount="${priceWithVat}"/></span></strong><br>
                                        <span class="text-silver"><small><fmt:message key="property.price-per-day" bundle="${lang}"/></small></span><br>
                                        <span class="text-silver"><small><small><fmt:message key="property.includes-vat-s" bundle="${lang}"/></small></small></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <tag:paginator currentPage="${currentPage}" totalPages="${totalPages}" baseUri="search"/>

    </jsp:body>
</tag:paginabasica>

