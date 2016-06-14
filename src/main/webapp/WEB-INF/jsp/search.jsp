<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
                        <h1><fmt:message key="search.no-results" bundle="${lang}"/> '${param.q}'. <fmt:message key="search.less-keywords" bundle="${lang}"/> </h1>
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><fmt:message key="property.title" bundle="${lang}"/> </th>
                            <th><fmt:message key="property.location" bundle="${lang}"/> <span class="glyphicon glyphicon-map-marker"></span> </th>
                            <th><fmt:message key="property.price-per-day" bundle="${lang}"/></th>
                            <th><fmt:message key="property.type" bundle="${lang}"/></th>
                            <th><fmt:message key="property.capacity" bundle="${lang}"/> (<span class="glyphicon glyphicon-user"></span> )</th>
                            <th><fmt:message key="property.floor-space" bundle="${lang}"/></th>
                        </tr>
                        </thead>
                        <tbody data-link="row" class="rowlink">
                        <c:forEach var="property" items="${properties}" varStatus="loop">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/property/show/${property.id}.html?q=${param.q}">${property.title}</a></td>
                                <td>${property.location}</td>
                                <td><t:show-price amount="${property.pricePerDay}"/></td>
                                <td>${property.type.label}</td>
                                <td>${property.capacity}</td>
                                <td>${property.floorSpace}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
        <tag:paginator currentPage="${currentPage}" totalPages="${totalPages}" baseUri="search"/>

    </jsp:body>
</tag:paginabasica>

