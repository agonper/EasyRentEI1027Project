<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" scope="request" value="${sessionScope.user}"/>
<nav class="navbar navbar-default">
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
            <c:when test="${user == null}">
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/login.html">ENTRAR</a></li>
                        <li id="signup-nav-btn" class="bg-cloud text-white"><a class="text-white" href="#">REGISTRASE</a></li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Logged in as ${user.username}</a></li>
                        <li id="logout-nav-btn" class="bg-cloud text-white"><a href="${pageContext.request.contextPath}/logout.html">Exit</a></li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>