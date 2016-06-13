<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<fmt:message key="proposal.title" bundle="${lang}" var="title"/>
<t:paginabasica title="${title}">
    <jsp:body>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/index.html"><fmt:message key="index.home" bundle="${lang}"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user/profile/${loggedUser.id}.html"><fmt:message key="profile.title" bundle="${lang}"/></a></li>
            <c:choose>
                <c:when test="${bookingProposal.tenant.equals(loggedUser)}">
                    <li><a href="${pageContext.request.contextPath}/user/tenant/${loggedUser.id}.html"><fmt:message key="tenant.title" bundle="${lang}"/></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/user/owner/${loggedUser.id}.html"><fmt:message key="owner.title" bundle="${lang}"/></a></li>
                </c:otherwise>
            </c:choose>
            <li class="active">${title}</li>
        </ol>
        <div class="row">
            <div class="page-header">
                <div class="row">
                    <div class="col-md-9">
                        <span class="h1">${title} <fmt:message key="general.for" bundle="${lang}"/>: <a href="${pageContext.request.contextPath}/property/show/${bookingProposal.property.id}.html">${bookingProposal.property.title}</a></span>
                    </div>
                    <br class="hidden-md hidden-lg">
                    <c:choose>
                        <c:when test="${bookingProposal.tenant.equals(loggedUser)}">
                            <div class="col-md-offset-2 col-md-1">
                                <a class="btn btn-warning" href="${pageContext.request.contextPath}/booking-proposal/edit/${bookingProposal.id}.html"><span class="glyphicon glyphicon-edit"></span> <fmt:message key="general.edit" bundle="${lang}"/></a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-md-3">
                                <c:if test="${bookingProposal.status == 'PENDING'}">
                                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/booking-proposal/accept/${bookingProposal.id}.html"><span class="glyphicon glyphicon-ok"></span> <fmt:message key="proposal.accept" bundle="${lang}"/></a>
                                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/booking-proposal/reject/${bookingProposal.id}.html"><span class="glyphicon glyphicon-remove"></span> <fmt:message key="proposal.reject" bundle="${lang}"/></a>
                                </c:if>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <fmt:message key="proposal.general-info" bundle="${lang}"/>
                    </div>
                    <ul class="list-group">
                        <c:if test="${bookingProposal.property.owner.equals(loggedUser)}">
                            <t:li-hb stringKey="proposal.tenant"><a href="${pageContext.request.contextPath}/user/profile/${bookingProposal.tenant.id}.html"><fmt:message key="general.link" bundle="${lang}"/> <span class="glyphicon glyphicon-new-window"></span></a></t:li-hb>
                        </c:if>
                        <t:li-hb stringKey="proposal.property"><a href="${pageContext.request.contextPath}/property/show/${bookingProposal.property.id}.html"><fmt:message key="general.link" bundle="${lang}"/> <span class="glyphicon glyphicon-new-window"></span></a></t:li-hb>
                        <t:li-hb stringKey="proposal.start-date">${bookingProposal.startDate}</t:li-hb>
                        <t:li-hb stringKey="proposal.end-date">${bookingProposal.endDate}</t:li-hb>
                        <t:li-hb stringKey="proposal.number-of-tenants">${bookingProposal.numberOfTenants}</t:li-hb>
                        <t:li-hb stringKey="proposal.amount"><t:show-price amount="${bookingProposal.totalAmount}"/></t:li-hb>
                    </ul>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <fmt:message key="proposal.status-info" bundle="${lang}"/>
                    </div>
                    <ul class="list-group">
                        <t:li-hb stringKey="proposal.status">${bookingProposal.status.label}</t:li-hb>
                        <t:li-hb stringKey="proposal.created-at">${bookingProposal.dateOfCreation}</t:li-hb>
                        <t:li-hb stringKey="proposal.last-updated">${bookingProposal.dateOfUpdate}</t:li-hb>
                    </ul>
                </div>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>