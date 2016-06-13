<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="subtitle" required="true" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<fmt:message key="add-property.title" bundle="${lang}" var="title"/>


<ol class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}/index.html"><fmt:message key="index.home" bundle="${lang}"/></a></li>
    <c:choose>
        <c:when test="${loggedUser.role == 'TENANT'}">
            <li class="active">${title}: ${subtitle}</li>
        </c:when>
        <c:otherwise>
            <li><a href="${pageContext.request.contextPath}/user/profile/${loggedUser.id}.html"><fmt:message key="profile.title" bundle="${lang}"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user/owner/${loggedUser.id}.html"><fmt:message key="owner.title" bundle="${lang}"/></a></li>
            <li class="active">${title}: ${subtitle}</li>
        </c:otherwise>
    </c:choose>
</ol>