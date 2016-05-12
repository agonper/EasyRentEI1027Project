<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:message key="profile.title" var="title" bundle="${lang}"/>
<t:paginabasica title="${title}: ${user.username}">
    <jsp:body>
        <span class="h1">${title}: ${user.username}</span>
        <a href="${pageContext.request.contextPath}/user/update/${user.id}.html" class="btn btn-warning">
            <span class="glyphicon glyphicon-edit">Edit</span>
        </a>
        <c:if test="${user.role == UserRole.ADMINISTRATOR}">
            <a href="${pageContext.request.contextPath}/user/profile/${user.id}.html" class="btn bg-warning">
                <span class="glyphicon glyphicon-ban-circle">Change state</span>
            </a>
        </c:if>
        <hr>
        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-warning" id="menu">
                    <div class="panel-heading">
                        Profile options
                    </div>
                    <div class="list-group">
                        <a class="list-group-item ${(param.size() == 0 or param.accountInfo != null) ? 'active' : ''}" href="?accountInfo#menu">
                            <fmt:message key="profile.account-info" bundle="${lang}"/>
                        </a>
                        <a class="list-group-item ${(param.personalData != null) ? 'active' : ''}" href="?personalData#menu">
                            <fmt:message key="profile.personal-data" bundle="${lang}"/>
                        </a>
                        <a class="list-group-item ${(param.addressInfo != null) ? 'active' : ''}" href="?addressInfo#menu">
                            <fmt:message key="profile.address-info" bundle="${lang}"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="panel panel-warning">
                    <c:if test="${param.size() == 0 or param.accountInfo != null}">
                        <div class="panel-heading">
                            <fmt:message key="profile.account-info" bundle="${lang}"/>
                        </div>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.picture" bundle="${lang}"/>:
                                </h4>
                                <div class="list-group-item-text">
                                        <c:choose>
                                            <c:when test="${user.photo == null}">
                                                <c:set var="photoUrl" value="${pageContext.request.contextPath}/img/profile-pic.png"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="photoUrl" value="${user.photo.filename}"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="md-size">
                                            <img class="img-circle img-responsive img-border" src="${photoUrl}">
                                        </div>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.username" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.username}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.email" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.email}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.user-type" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.role}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.signup-date" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.signUpDate}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.active" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.active}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.deactivated-since" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.deactivatedSince}
                                </p>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${param.personalData != null}">
                        <div class="panel-heading">
                            <fmt:message key="profile.personal-data" bundle="${lang}"/>
                        </div>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.name" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.username}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.surnames" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.surnames}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.dni" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.dni}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.phone-number" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.phoneNumber}
                                </p>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${param.addressInfo != null}">
                        <div class="panel-heading">
                            <fmt:message key="profile.address-info" bundle="${lang}"/>
                        </div>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.address" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.postalAddress}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.country" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.country}
                                </p>
                            </li>
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <fmt:message key="user.post-code" bundle="${lang}"/>:
                                </h4>
                                <p class="list-group-item-text">
                                        ${user.postCode}
                                </p>
                            </li>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>

    </jsp:body>
</t:paginabasica>