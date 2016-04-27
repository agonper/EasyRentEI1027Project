<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:paginabasica>
    <jsp:body>
        <h1>Listado de usuarios</h1>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/add.html">
            <span class="glyphicon glyphicon-plus"></span> Add user
        </a>
        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>DNI</th>
                    <th>Role</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Surnames</th>
                    <th>Email</th>
                    <th>Phone number</th>
                    <th>Postal address</th>
                    <th>Country</th>
                    <th>Post code</th>
                    <th>Sign up date</th>
                    <th>Active</th>
                    <th>Deactivated since</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.DNI}</td>
                        <td>${user.role}</td>
                        <td>${user.password}</td>
                        <td>${user.name}</td>
                        <td>${user.surnames}</td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.postalAddress}</td>
                        <td>${user.country}</td>
                        <td>${user.postCode}</td>
                        <td>${user.signUpDate}</td>
                        <td>${user.active}</td>
                        <td>${user.deactivatedSince}</td>
                        <td><a href="${pageContext.request.contextPath}/user/update.html" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a></td>
                        <td><a href="${pageContext.request.contextPath}/user/delete.html" class="btn btn-primary"><span class="glyphicon glyphicon-remove"></span></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:body>
</t:paginabasica>