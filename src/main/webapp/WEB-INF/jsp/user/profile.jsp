<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:message key="profile.title" var="title" bundle="${lang}"/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<t:paginabasica title="${title}: ${user.username}">
    <jsp:body>
        <span class="h1">${title}: ${user.username}</span>
        <%-- FIXME Y esto?--%>
        <c:if test="${loggedUser.role == UserRole.ADMINISTRATOR}">
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
                        <c:if test="${user.equals(loggedUser)}">
                            <a class="list-group-item ${(param.personalData != null) ? 'active' : ''} ${user.equals(loggedUser) ? '' : 'disabled'}" href="?personalData#menu">
                                <fmt:message key="profile.personal-data" bundle="${lang}"/>
                            </a>
                            <a class="list-group-item ${(param.addressInfo != null) ? 'active' : ''} ${user.equals(loggedUser) ? '' : 'disabled'}" href="?addressInfo#menu">
                                <fmt:message key="profile.address-info" bundle="${lang}"/>
                            </a>
                        </c:if>
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
                            <t:li-hb stringKey="user.picture">
                                <c:choose>
                                    <c:when test="${user.photo == null}">
                                        <c:set var="photoUrl" value="${pageContext.request.contextPath}/img/profile-pic.png"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="photoUrl" value="${pageContext.request.contextPath}/uploads/profile-pics/${user.photo.filename}"/>
                                    </c:otherwise>
                                </c:choose>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="md-size">
                                            <img class="img-circle img-responsive img-border" src="${photoUrl}">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <c:if test="${user.equals(loggedUser)}">
                                            <form class="form-horizontal" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/user/edit/${user.id}/upload-picture">
                                                <div class="form-group">
                                                    <label for="file"><fmt:message key="profile.upload-picture" bundle="${lang}"/></label>
                                                    <input type="file" name="file" accept="image/**" id="file">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                </div>
                                                <div class="form-group">
                                                    <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-upload"></span> <fmt:message key="general.upload" bundle="${lang}"/></button>
                                                </div>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                            </t:li-hb>
                            <t:li-hb stringKey="user.username">${user.username}</t:li-hb>
                            <t:li-hb stringKey="user.email">${user.email}</t:li-hb>
                            <t:li-hb stringKey="user.user-type">${user.role.toString().substring(0,1).toUpperCase()}${user.role.toString().substring(1)}</t:li-hb>
                            <t:li-hb stringKey="user.signup-date">${user.signUpDate}</t:li-hb>
                        </ul>
                        <c:if test="${user.equals(loggedUser)}">
                            <div class="panel-footer">
                                <a class="btn btn-warning" href="${pageContext.request.contextPath}/user/edit/${user.id}/account-info">
                                    <span class="glyphicon glyphicon-edit"></span> <fmt:message key="general.edit" bundle="${lang}"/>
                                </a>
                                <a class="btn btn-warning" href="${pageContext.request.contextPath}/user/edit/${user.id}/change-password">
                                    <fmt:message key="profile.change-password" bundle="${lang}"/>
                                </a>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${user.equals(loggedUser) and param.personalData != null}">
                        <div class="panel-heading">
                            <fmt:message key="profile.personal-data" bundle="${lang}"/>
                        </div>
                        <ul class="list-group">
                            <t:li-hb stringKey="user.name">${user.name}</t:li-hb>
                            <t:li-hb stringKey="user.surnames">${user.surnames}</t:li-hb>
                            <t:li-hb stringKey="user.dni">${user.dni}</t:li-hb>
                            <t:li-hb stringKey="user.phone-number">${user.phoneNumber}</t:li-hb>
                        </ul>
                        <div class="panel-footer">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/user/edit/${user.id}/personal-data">
                                <span class="glyphicon glyphicon-edit"></span> <fmt:message key="general.edit" bundle="${lang}"/>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${user.equals(loggedUser) and param.addressInfo != null}">
                        <div class="panel-heading">
                            <fmt:message key="profile.address-info" bundle="${lang}"/>
                        </div>
                        <ul class="list-group">
                            <t:li-hb stringKey="user.address">${user.postalAddress}</t:li-hb>
                            <t:li-hb stringKey="user.country">${user.country}</t:li-hb>
                            <t:li-hb stringKey="user.post-code">${user.postCode}</t:li-hb>
                        </ul>
                        <div class="panel-footer">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/user/edit/${user.id}/address-info">
                                <span class="glyphicon glyphicon-edit"></span> <fmt:message key="general.edit" bundle="${lang}"/>
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

    </jsp:body>
</t:paginabasica>