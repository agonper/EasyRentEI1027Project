<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags/models" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<fmt:message key="general.by" bundle="${lang}" var="by"/>
<t:paginabasica title="${property.title}">
    <jsp:body>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/index.html"><fmt:message key="index.home" bundle="${lang}"/></a></li>
            <c:if test="${not empty param.q}">
                <li><a href="${pageContext.request.contextPath}/search.html?q=${param.q}"><fmt:message key="general.search" bundle="${lang}"/></a></li>
            </c:if>
            <c:if test="${property.owner.equals(loggedUser)}">
                <li><a href="${pageContext.request.contextPath}/index.html#owner-properties"><fmt:message key="home.my-properties" bundle="${lang}"/></a></li>
            </c:if>
            <li class="active">${property.title}</li>
        </ol>
        <div class="page-header">
            <div class="row">
                <div class="col-md-11">
                    <span class="h1">${property.title}  <small>${by} <a href="${pageContext.request.contextPath}/user/profile/${property.owner.id}.html">${property.owner.username}</a></small></span>
                </div>
                <br class="hidden-md hidden-lg">
                <div class="col-md-1">
                    <c:choose>
                        <c:when test="${property.owner.equals(loggedUser)}">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/property/edit/${property.id}.html"><span class="glyphicon glyphicon-edit"></span> <fmt:message key="general.edit" bundle="${lang}"/></a>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${empty property.availabilityPeriods}">
                                    <a class="btn btn-warning disabled" href="#"><span class="glyphicon glyphicon-ok"></span> <fmt:message key="proposal.book" bundle="${lang}"/> </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/property/booking-proposal/${property.id}.html${not empty param.q ? '?q=' : ''}${not empty param.q ? param.q : ''}"><span class="glyphicon glyphicon-ok"></span> <fmt:message key="proposal.book" bundle="${lang}"/> </a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <m:property property="${property}"/>
    </jsp:body>
</t:paginabasica>