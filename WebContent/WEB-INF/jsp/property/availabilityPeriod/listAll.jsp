<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="coreActions" uri="http://java.sun.com/jsp/jstl/core"%>

<tag:paginabasica title="List all availability periods">
    <jsp:body>
        <h1>List of availability periods</h1>

        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Start date</th>
                    <th>End date</th>
                </tr>
                <coreActions:forEach var="availabilityPeriod" items="${availabilityPeriods}">
                    <tr>
                        <td>${availabilityPeriod.id}</td>
                        <td>${availabilityPeriod.startDate}</td>
                        <td>${availabilityPeriod.endDate}</td>
                        <td><a href="${pageContext.request.contextPath}#" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a></td>
                    </tr>
                </coreActions:forEach>
            </table>
        </div>
        <a href="../index.jsp">Go back to the index page</a>
    </jsp:body>
</tag:paginabasica>