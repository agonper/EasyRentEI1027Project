<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ attribute name="user" required="true" type="es.uji.daal.easyrent.model.User" %>
<%@ attribute name="location" required="true" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<c:if test="${user.equals(loggedUser)}">
    <ul class="nav nav-tabs nav-justified" id="user-options">
        <li role="presentation" class="${location.equals("profile") ? 'active' : ''}"><a href="${pageContext.request.contextPath}/user/profile/${user.id}.html#user-options"><fmt:message key="profile.title" bundle="${lang}"/></a></li>
        <c:if test="${user.role == 'OWNER'}">
            <li role="presentation" class="${location.equals("owner") ? 'active' : ''}"><a href="${pageContext.request.contextPath}/user/owner/${user.id}.html#user-options"><fmt:message key="owner.title" bundle="${lang}"/></a></li>
        </c:if>
        <li role="presentation" class="${location.equals("tenant") ? 'active' : ''}"><a href="${pageContext.request.contextPath}/user/tenant/${user.id}.html#user-options"><fmt:message key="tenant.title" bundle="${lang}"/></a></li>
        <li role="presentation" class="${location.equals("notifications") ? 'active' : ''}"><a href="${pageContext.request.contextPath}/user/notifications/${user.id}.html#user-options"><fmt:message key="notifications.title" bundle="${lang}"/> <span class="badge">0</span></a></li>
    </ul>
    <br>
</c:if>