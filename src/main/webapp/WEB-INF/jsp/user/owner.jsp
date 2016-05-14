<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:message key="owner.title" var="title" bundle="${lang}"/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<t:paginabasica title="${title}: ${user.username}">
    <jsp:body>
        <span class="h1">${title}: ${user.username}</span>
        <hr>
        <t:user-options user="${user}" location="owner"/>
        <div class="row">
            <div class="col-md-3">
                <div class="panel panel-warning" id="sub-menu">
                    <div class="panel-heading">
                        <fmt:message key="owner.sections" bundle="${lang}"/>
                    </div>
                    <div class="list-group">
                        <a class="list-group-item ${(param.size() == 0 or param.properties != null) ? 'active' : ''}" href="?properties#sub-menu">
                            <fmt:message key="owner.properties" bundle="${lang}"/> <span class="badge">${user.properties.size()}</span>
                        </a>
                        <a class="list-group-item ${(param.proposals != null) ? 'active' : ''}" href="?proposals#sub-menu">
                            <fmt:message key="owner.received-proposals" bundle="${lang}"/> <span class="badge">${bookingProposals.size()}</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div class="panel panel-warning">
                    <c:if test="${param.size() == 0 or param.properties != null}">
                        <div class="panel-heading">
                            <fmt:message key="owner.properties" bundle="${lang}"/>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <tr>
                                    <th><fmt:message key="property.number" bundle="${lang}"/> </th>
                                    <th><fmt:message key="property.title" bundle="${lang}"/> </th>
                                    <th><fmt:message key="property.price-per-day" bundle="${lang}"/></th>
                                    <th><fmt:message key="property.type" bundle="${lang}"/></th>
                                    <th><fmt:message key="property.creation-date" bundle="${lang}"/></th>
                                    <th><fmt:message key="general.edit" bundle="${lang}"/></th>
                                    <th><fmt:message key="general.delete" bundle="${lang}"/></th>
                                </tr>
                                <c:forEach var="property" items="${user.properties}" varStatus="loop">
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/property/show/${property.id}.html">${loop.index+1}</a></td>
                                        <td>${property.title}</td>
                                        <td><fmt:formatNumber currencySymbol="€" type="currency" value="${property.pricePerDay}" pattern="####.00 ¤"/></td>
                                        <td>${property.type.label}</td>
                                        <td>${property.creationDate}</td>
                                        <td><a class="btn btn-warning" href="${pageContext.request.contextPath}/property/edit/${property.id}.html"><span class="glyphicon glyphicon-edit"></span></a></td>
                                        <td><a class="btn btn-warning" href="${pageContext.request.contextPath}/property/delete/${property.id}.html"><span class="glyphicon glyphicon-remove"></span></a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div class="panel-footer">
                            <td><a class="btn btn-warning" href="${pageContext.request.contextPath}/property/add.html"><span class="glyphicon glyphicon-plus"></span> <fmt:message key="general.add" bundle="${lang}"/></a></td>
                        </div>
                    </c:if>
                    <c:if test="${param.proposals != null}">
                        <div class="panel-heading">
                            <fmt:message key="owner.received-proposals" bundle="${lang}"/>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <tr>
                                    <th><fmt:message key="proposal.number" bundle="${lang}"/> </th>
                                    <th><fmt:message key="proposal.property" bundle="${lang}"/> </th>
                                    <th><fmt:message key="proposal.tenant" bundle="${lang}"/> </th>
                                    <th><fmt:message key="proposal.start-date" bundle="${lang}"/></th>
                                    <th><fmt:message key="proposal.end-date" bundle="${lang}"/></th>
                                    <th><fmt:message key="proposal.status" bundle="${lang}"/></th>
                                    <th><fmt:message key="proposal.created-at" bundle="${lang}"/></th>
                                    <th><fmt:message key="proposal.last-updated" bundle="${lang}"/></th>
                                    <th><fmt:message key="proposal.accept" bundle="${lang}"/></th>
                                    <th><fmt:message key="proposal.reject" bundle="${lang}"/></th>
                                </tr>
                                <c:forEach var="bookingProposal" items="${bookingProposals}" varStatus="loop">
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/booking-proposals/show/${bookingProposal.id}.html">${loop.index+1}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/property/show/${bookingProposal.property.id}.html"><fmt:message key="general.link" bundle="${lang}"/> <span class="glyphicon glyphicon-new-window"></span> </a></td>
                                        <td><a href="${pageContext.request.contextPath}/user/profile/${bookingProposal.tenant.id}.html"><fmt:message key="general.link" bundle="${lang}"/> <span class="glyphicon glyphicon-new-window"></a></td>
                                        <td>${bookingProposal.startDate}</td>
                                        <td>${bookingProposal.endDate}</td>
                                        <td>${bookingProposal.status.label}</td>
                                        <td>${bookingProposal.dateOfCreation}</td>
                                        <td>${bookingProposal.dateOfUpdate}</td>
                                        <td><a class="btn btn-warning" href="${pageContext.request.contextPath}/booking-proposal/accept/${bookingProposal.id}.html"><span class="glyphicon glyphicon-check"></span></a></td>
                                        <td><a class="btn btn-warning" href="${pageContext.request.contextPath}/property/reject/${bookingProposal.id}.html"><span class="glyphicon glyphicon-remove"></span></a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>