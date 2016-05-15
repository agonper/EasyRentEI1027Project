<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:message key="administration.title" var="title" bundle="${lang}"/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<t:paginabasica title="${title}">
    <jsp:body>
        <div class="page-header">
            <span class="h1">${title}</span>
        </div>
        <div class="container">
            <hr>
            <t:administration-options location="properties"/>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-warning top-padding">
                        <div class="panel-heading">Search for properties</div>
                        <div class="panel-body">
                            <form:form cssClass="form-horizontal" method="post" action="/searchProperties" modelAttribute="property">
                                <input type="text">
                                <select/>
                                <input type="submit" class="btn btn-warning"/>
                            </form:form>
                        </div>

                        <div class="panel-heading">List of searched properties</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <tr>
                                        <th>ID</th>
                                        <th>Owner id</th>
                                        <th>Title</th>
                                        <th>Location</th>
                                        <th>Rooms</th>
                                        <th>Capacity</th>
                                        <th>Beds</th>
                                        <th>Bathrooms</th>
                                        <th>Floor space</th>
                                        <th>Price per day</th>
                                        <th>Creation date</th>
                                        <th>Type</th>
                                        <th>Description</th>
                                    </tr>
                                    <c:forEach var="property" items="${properties}">
                                        <tr>
                                            <td>${property.id}</td>
                                            <td>${property.owner.id}</td>
                                            <td>${property.title}</td>
                                            <td>${property.location}</td>
                                            <td>${property.rooms}</td>
                                            <td>${property.capacity}</td>
                                            <td>${property.beds}</td>
                                            <td>${property.bathrooms}</td>
                                            <td>${property.floorSpace}</td>
                                            <td>${property.pricePerDay}</td>
                                            <td>${property.creationDate}</td>
                                            <td>${property.type.label}</td>
                                            <td>${property.description}</td>
                                            <td><a href="${pageContext.request.contextPath}/property/update/${property.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span></a></td>
                                            <td><a href="${pageContext.request.contextPath}/property/delete/${property.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>