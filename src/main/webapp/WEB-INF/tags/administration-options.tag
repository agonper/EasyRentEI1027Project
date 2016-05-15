<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ attribute name="location" required="true" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<c:if test="${user.equals(loggedUser)}">
    <ul class="nav nav-tabs nav-justified" id="administration-options">
        <li role="presentation" class="${location.equals("users") ? 'active' : ''}"><a a data-toggle="tab" href="${pageContext.request.contextPath}/administration/users.html#administration-options"><fmt:message key="administration.users" bundle="${lang}"/></a></li>
        <li role="presentation" class="${location.equals("properties") ? 'active' : ''}"><a a data-toggle="tab" href="${pageContext.request.contextPath}/administration/properties.html#administration-options"><fmt:message key="administration.properties" bundle="${lang}"/></a></li>
        <li role="presentation" class="${location.equals("tenant") ? 'active' : ''}"><a a data-toggle="tab" href="${pageContext.request.contextPath}/administration/booking_proposals.html#administration-options"><fmt:message key="administration.booking-proposals" bundle="${lang}"/></a></li>
        <li role="presentation" class="${location.equals("invoices") ? 'active' : ''}"><a a data-toggle="tab" href="${pageContext.request.contextPath}/administration/invoices.html#administration-options"><fmt:message key="administration.invoices" bundle="${lang}"/></a></li>
    </ul>
    <br>
</c:if>
