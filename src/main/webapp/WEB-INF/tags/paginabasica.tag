<%@ tag description="EasyRent boilerplate"
        pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="er" uri="/WEB-INF/easy-rent.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <!-- Estils propis -->
    <link
            href="${pageContext.request.contextPath}/css/easyrent.css"
            rel="stylesheet">
</head>
<body>
<c:set var="locale" value="en"/>
<sec:authorize access="isAuthenticated()">
    <sec:authentication var="user" property="principal" />
    <%-- TODO Set user prefered languaje --%>
    <c:set var="locale" value="en"/>
</sec:authorize>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="lang" scope="application"/>

<t:navigation/>
<header class="jumbotron text-center">
    <h1>EasyRent</h1>
    <p><fmt:message key="easyrent.slogan" bundle="${lang}" /></p>
    <form class="form-inline" method="get" action="${pageContext.request.contextPath}/search.html">
        <div class="input-group">
            <fmt:message key="home.search" bundle="${lang}" var="search"/>
            <input class="form-control" name="q" placeholder="${search}" size="50">
            <div class="input-group-btn">
                <button type="submit" class="btn btn-default"><fmt:message key="home.search-btn" bundle="${lang}"/> </button>
            </div>
        </div>
    </form>
</header>
<div class="container container-padded">
    <jsp:doBody />
</div>
<footer class="container-fluid container-padded bg-cloud">
    <hr>
    <p class="text-white text-center">
        &copy;<er:year-tag/> - <fmt:message key="easyrent.project" bundle="${lang}"/>
    </p>
</footer>
</body>
</html>
