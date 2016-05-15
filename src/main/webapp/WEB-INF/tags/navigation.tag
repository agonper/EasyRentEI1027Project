<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="user" property="principal" />
</sec:authorize>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><span class="glyphicon glyphicon-home"></span></a>
        </div>
        <c:choose>
            <c:when test="${user == null || user.id == null}">
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="${pageContext.request.contextPath}/login.html">
                                <span class="glyphicon glyphicon-log-in"></span> <fmt:message key="navigation.login" bundle="${lang}"/>
                            </a>
                        </li>
                        <li id="signup-nav-btn" class="bg-cloud text-white">
                            <a class="text-white" href="${pageContext.request.contextPath}/signup.html">
                                <fmt:message key="navigation.signup" bundle="${lang}"/>
                            </a>
                        </li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <fmt:message key="add-property.add" bundle="${lang}" var="addPropertyBtn"/>
                            <a href="${pageContext.request.contextPath}/property/add.html" title="${addPropertyBtn}">
                                <span class="glyphicon glyphicon-plus"></span>
                            </a>
                        </li>
                        <li>
                            <fmt:message key="notifications.title" bundle="${lang}" var="notificationsBtn"/>
                            <a href="${pageContext.request.contextPath}/user/notifications/${user.id}.html" title="${notificationsBtn}">
                                <span class="glyphicon glyphicon-bell"></span> <span class="badge">0</span>
                            </a>
                        </li>
                        <li class="divider-vertical"></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/profile/${user.id}.html">
                                <span class="glyphicon glyphicon-user"></span> ${user.username.toUpperCase()}
                            </a>
                        </li>
                        <li id="logout-nav-btn" class="bg-cloud text-white">
                            <a href="<c:url value="/logout.html" />" class="bg-cloud text-white">
                                <span class="glyphicon glyphicon-log-out"></span> <fmt:message key="navigation.logout" bundle="${lang}"/>
                            </a>
                        </li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>