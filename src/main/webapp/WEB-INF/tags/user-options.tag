<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ attribute name="user" required="true" type="es.uji.daal.easyrent.model.User" %>
<%@ attribute name="location" required="true" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<c:if test="${user.equals(loggedUser)}">
    <ul class="nav nav-tabs nav-justified">
        <li role="presentation" class="${location.equals("profile") ? 'active' : ''}"><a href="#"><fmt:message key="profile.title" bundle="${lang}"/></a></li>
        <c:if test="${user.role == 'TENANT'}">
            <li role="presentation" class="${location.equals("owner") ? 'active' : ''}"><a href="#"><fmt:message key="owner.title" bundle="${lang}"/></a></li>
        </c:if>
        <li role="presentation" class="${location.equals("tenant") ? 'active' : ''}"><a href="#"><fmt:message key="tenant.title" bundle="${lang}"/></a></li>
        <li role="presentation" class="${location.equals("notifications") ? 'active' : ''}"><a href="#"><fmt:message key="notifications.title" bundle="${lang}"/> <span class="badge">0</span></a></li>
    </ul>
    <br>
</c:if>