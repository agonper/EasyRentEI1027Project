<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<tag:paginabasica title="EasyRent">
    <jsp:body>
        <h1><fmt:message key="search.title" bundle="${lang}"/></h1>
        <hr>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <tr>
                    <th><fmt:message key="property.title" bundle="${lang}"/> </th>
                    <th><fmt:message key="property.location" bundle="${lang}"/> <span class="glyphicon glyphicon-map-marker"></span> </th>
                    <th><fmt:message key="property.price-per-day" bundle="${lang}"/></th>
                    <th><fmt:message key="property.type" bundle="${lang}"/></th>
                    <th><fmt:message key="property.capacity" bundle="${lang}"/> (<span class="glyphicon glyphicon-user"></span> )</th>
                    <th><fmt:message key="property.floor-space" bundle="${lang}"/></th>

                </tr>
                <c:forEach var="property" items="${properties}" varStatus="loop">
                    <tr>
                        <td>${property.title}</td>
                        <td>${property.location}</td>
                        <td><t:show-price amount="${property.pricePerDay}"/></td>
                        <td>${property.type.label}</td>
                        <td>${property.capacity}</td>
                        <td>${property.floorSpace}</td>
                    </tr>
                </c:forEach>
            </table>
            Page: ${currentPage}
            Count: ${totalPages}
        </div>
    </jsp:body>
</tag:paginabasica>

