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
        <div class="page-header">
            <span class="h1">${title}</span>
        </div>
        <div class="container">
            <hr>
            <t:administration-options location="booking_proposals"/>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-warning top-padding30px">
                        <div class="panel-heading">Search for booking proposals</div>
                        <div class="panel-body">

                            <form class="form-inline" method="get" action="/searchBookingProposals">
                                <div class="input-group">
                                    <input class="form-control" name="bookingProposalAttribute" placeholder="Search for booking proposals" value="" size="80">
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-warning">Search </button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="selectServiceAttribute" class="left-padding30px">
                                        <fmt:message key="search.by-attribute" bundle="${lang}"/>
                                    </label>
                                    <select id="selectServiceAttribute" class="form-control">
                                        <option>-</option>
                                    </select>
                                </div>
                            </form>

                        </div>

                        <div class="panel-heading">List of searched booking proposals</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <tr>
                                        <th>ID</th>
                                        <th>Property ID</th>
                                        <th>Tenant ID</th>
                                        <th>Start date</th>
                                        <th>End date</th>
                                        <th>Status</th>
                                        <th>Payment reference</th>
                                        <th>Total amount</th>
                                        <th>Number of tenants</th>
                                        <th>Date of creation</th>
                                        <th>Date of acceptation</th>
                                        <th>Invoice</th>
                                        <th>Delete?</th>
                                    </tr>
                                    <c:forEach var="bookingProposal" items="${bookingProposals}">
                                        <tr>
                                            <td>${bookingProposal.id}</td>
                                            <td>${bookingProposal.property.id}</td>
                                            <td>${bookingProposal.tenant.id}</td>
                                            <td>${bookingProposal.startDate}</td>
                                            <td>${bookingProposal.endDate}</td>
                                            <td>${bookingProposal.status.toString()}</td>
                                            <td>${bookingProposal.paymentReference}</td>
                                            <td>${bookingProposal.totalAmount}</td>
                                            <td>${bookingProposal.numberOfTenants}</td>
                                            <td>${bookingProposal.dateOfCreation}</td>
                                            <td>${bookingProposal.dateOfUpdate}</td>
                                            <td>${bookingProposal.invoice.number}</td>
                                            <td><a href="${pageContext.request.contextPath}/bookingProposal/delete/${bookingProposal.id}.html" class="btn btn-primary"><span class="glyphicon glyphicon-remove"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:paginabasica>