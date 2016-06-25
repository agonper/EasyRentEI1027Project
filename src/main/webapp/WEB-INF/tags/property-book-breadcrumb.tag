<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="subtitle" required="true" %>

<fmt:message key="book-property.title" bundle="${lang}" var="title"/>
<ol class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}/index.html"><fmt:message key="index.home" bundle="${lang}"/></a></li>
    <c:if test="${not empty param.q}">
        <li><a href="${pageContext.request.contextPath}/search.html?q=${param.q}"><fmt:message key="general.search" bundle="${lang}"/></a></li>
    </c:if>
    <li><a href="${pageContext.request.contextPath}/property/show/${property.id}.html${not empty param.q ? '?q=' : ''}${not empty param.q ? param.q : ''}"><c:out value="${property.title}"/></a></li>
    <li class="active">${title}: ${subtitle}</li>
</ol>