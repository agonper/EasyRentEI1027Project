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
            <t:administration-options location="invoices"/>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-warning top-padding30px">
                        <div class="panel-heading"><fmt:message key="administration-invoices.searchForInvoices" bundle="${lang}"/></div>
                        <div class="panel-body">

                            <form class="form-inline" method="get" action="/administration/invoices/searchFor">
                                <div class="form-group">
                                    <label for="selectInvoiceAttribute" class="left-padding30px">
                                        <fmt:message key="search.by-attribute" bundle="${lang}"/>
                                    </label>
                                    <select id="selectInvoiceAttribute" class="form-control">
                                        <option value="number"><fmt:message key="invoice.number" bundle="${lang}"/></option>
                                        <option value="ID">ID</option>
                                        <option value="booking"><fmt:message key="invoice.booking" bundle="${lang}"/></option>
                                        <option value="vat"><fmt:message key="invoice.vat" bundle="${lang}"/></option>
                                        <option value="address"><fmt:message key="invoice.address" bundle="${lang}"/></option>
                                        <option value="expeditionDate"><fmt:message key="invoice.expedition-date" bundle="${lang}"/></option>
                                        <option value="totalAmount"><fmt:message key="invoice.total-amount" bundle="${lang}"/></option>
                                    </select>
                                </div>

                                <div class="input-group">
                                    <input class="form-control" name="invoiceAttribute" placeholder="Search for invoices" value="" size="80">
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-warning"><fmt:message key="administration.search" bundle="${lang}"/></button>
                                    </div>
                                </div>
                            </form>

                        </div>

                        <div class="panel-heading"><fmt:message key="administration-invoices.listOfSearchedInvoices" bundle="${lang}"/></div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <tr>
                                        <th>ID</th>
                                        <th><fmt:message key="invoice.booking" bundle="${lang}"/></th>
                                        <th><fmt:message key="invoice.number" bundle="${lang}"/></th>
                                        <th><fmt:message key="invoice.vat" bundle="${lang}"/></th>
                                        <th><fmt:message key="invoice.address" bundle="${lang}"/></th>
                                        <th><fmt:message key="invoice.expedition-date" bundle="${lang}"/></th>
                                        <th><fmt:message key="invoice.total-amount" bundle="${lang}"/></th>
                                    </tr>
                                    <c:forEach var="invoice" items="${invoices}">
                                        <tr>
                                            <td>${invoice.id}</td>
                                            <td>${invoice.proposal.id}</td>
                                            <td>${invoice.number}</td>
                                            <td>${invoice.vat}</td>
                                            <td>${invoice.address}</td>
                                            <td>${invoice.expeditionDate}</td>
                                            <td>${invoice.proposal.totalAmount}</td>
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