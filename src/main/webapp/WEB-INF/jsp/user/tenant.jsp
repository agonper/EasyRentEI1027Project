<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:message key="tenant.title" var="title" bundle="${lang}"/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<t:paginabasica title="${title}: ${user.username}">
    <jsp:body>
        <div class="page-header">
            <span class="h1">${title}: ${user.username}</span>
        </div>
        <t:user-options user="${user}" location="tenant"/>
        <div class="row">
            <div class="col-md-3">
                <div class="panel panel-warning" id="sub-menu">
                    <div class="panel-heading">
                        <fmt:message key="tenant.sections" bundle="${lang}"/>
                    </div>
                    <div class="list-group">
                        <a class="list-group-item ${(param.size() == 0 or param.proposals != null) ? 'active' : ''}" href="?proposals#sub-menu">
                            <fmt:message key="tenant.emited-proposals" bundle="${lang}"/> <span class="badge">${user.bookingProposals.size()}</span>
                        </a>
                        <a class="list-group-item ${(param.invoices != null) ? 'active' : ''}" href="?invoices#sub-menu">
                            <fmt:message key="tenant.invoices" bundle="${lang}"/> <span class="badge">${invoices.size()}</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div class="panel panel-warning">
                    <c:if test="${param.size() == 0 or param.proposals != null}">
                        <div class="panel-heading">
                            <fmt:message key="tenant.emited-proposals" bundle="${lang}"/>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th><fmt:message key="general.number" bundle="${lang}"/> </th>
                                        <th><fmt:message key="proposal.property" bundle="${lang}"/> </th>
                                        <th><fmt:message key="proposal.start-date" bundle="${lang}"/></th>
                                        <th><fmt:message key="proposal.end-date" bundle="${lang}"/></th>
                                        <th><fmt:message key="proposal.status" bundle="${lang}"/></th>
                                        <th><fmt:message key="proposal.created-at" bundle="${lang}"/></th>
                                        <th><fmt:message key="proposal.last-updated" bundle="${lang}"/></th>
                                        <th><fmt:message key="general.edit" bundle="${lang}"/></th>
                                    </tr>
                                </thead>
                                <tbody data-link="row" class="rowlink">
                                    <c:forEach var="bookingProposal" items="${user.bookingProposals}" varStatus="loop">
                                        <tr>
                                            <td><a href="${pageContext.request.contextPath}/booking-proposal/show/${bookingProposal.id}.html">${loop.index+1}</a></td>
                                            <td class="rowlink-skip"><a href="${pageContext.request.contextPath}/property/show/${bookingProposal.property.id}.html"><fmt:message key="general.link" bundle="${lang}"/> <span class="glyphicon glyphicon-new-window"></span> </a></td>
                                            <td>${bookingProposal.startDate}</td>
                                            <td>${bookingProposal.endDate}</td>
                                            <td>${bookingProposal.status.label}</td>
                                            <td>${bookingProposal.dateOfCreation}</td>
                                            <td>${bookingProposal.dateOfUpdate}</td>
                                            <td class="rowlink-skip"><a class="btn btn-warning" href="${pageContext.request.contextPath}/booking-proposal/edit/${bookingProposal.id}.html"><span class="glyphicon glyphicon-edit"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    <c:if test="${param.invoices != null}">
                        <div class="panel-heading">
                            <fmt:message key="tenant.invoices" bundle="${lang}"/>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th><fmt:message key="general.number" bundle="${lang}"/> </th>
                                        <th><fmt:message key="invoice.booking" bundle="${lang}"/> </th>
                                        <th><fmt:message key="invoice.expedition-date" bundle="${lang}"/> </th>
                                        <th><fmt:message key="invoice.total-amount" bundle="${lang}"/></th>
                                        <th><fmt:message key="invoice.pdf" bundle="${lang}"/></th>
                                    </tr>
                                </thead>
                                <tbody data-link="row" class="rowlink">
                                    <c:forEach var="invoice" items="${invoices}">
                                        <tr>
                                            <td><a href="${pageContext.request.contextPath}/invoice/show/${invoice.id}.html">${invoice.number}</a></td>
                                            <td class="rowlink-skip"><a href="${pageContext.request.contextPath}/booking-proposal/show/${invoice.proposal}.html"><fmt:message key="general.link" bundle="${lang}"/> <span class="glyphicon glyphicon-new-window"></span> </a></td>
                                            <td>${invoice.expeditionDate}</td>
                                            <td><t:show-price amount="${(invoice.vat+1)*invoice.proposal.totalAmount}"/></td>
                                            <td class="rowlink-skip"><a class="btn btn-warning" href="${pageContext.request.contextPath}/invoice/pdf/${bookingProposal.id}.html"><span class="glyphicon glyphicon-file"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>