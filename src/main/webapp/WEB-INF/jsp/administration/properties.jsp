<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:message key="administration.title" var="title" bundle="${lang}"/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="loggedUser" property="principal" />
</sec:authorize>

<t:paginabasica title="${title}">
    <jsp:body>
        <span class="h1">${title}</span>
        <div class="container">
            <div class="row">
                <hr>
                <div class="col-md-12">
                    <ul class="nav nav-tabs">
                        <li><a data-toggle="tab" href="${pageContext.request.contextPath}/administration/users">Users management</a></li>
                        <li class="active"><a data-toggle="tab" href="${pageContext.request.contextPath}/administration/properties">Properties management</a></li>
                        <li><a data-toggle="tab" href="${pageContext.request.contextPath}/administration/booking_proposals">Booking proposals management</a></li>
                        <li><a data-toggle="tab" href="${pageContext.request.contextPath}/administration/invoices">Invoices control</a></li>
                    </ul>

                    <div class="tab-content">
                        <div id="users" class="tab-pane fade in active">
                            <div class="panel panel-warning top-padding">
                                <div class="panel-heading">Search for properties</div>
                                <div class="panel-body">
                                    <form:form cssClass="form-horizontal" method="post" action="/searchUsers" modelAttribute="user">
                                        <div class="form-group">
                                            <input type="text">

                                            <button type="submit" class="btn btn-warning">
                                                <fmt:message key="general.search" bundle="${lang}"/>
                                            </button>
                                        </div>
                                    </form:form>
                                </div>
                                <div class="panel-heading">List of searched properties</div>
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table">

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="properties" class="tab-pane fade">

                        </div>
                        <div id="booking_proposals" class="tab-pane fade">

                        </div>
                        <div id="invoices" class="tab-pane fade">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>
