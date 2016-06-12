<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fm" tagdir="/WEB-INF/tags/forms" %>
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
            <t:administration-options location="users"/>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-warning top-padding30px">
                        <div class="panel-heading"><fmt:message key="administration-users.searchForUsers" bundle="${lang}"/></div>
                        <div class="panel-body">

                            <form class="form-inline" method="get" action="/administration/users/searchFor">

                                <div class="form-group">
                                    <label for="selectedUserAttribute" class="left-padding30px">
                                        <fmt:message key="search.by-attribute" bundle="${lang}"/>
                                    </label>

                                    <select id="selectedUserAttribute" onchange="changedSelectValue()" name="selectedUserAttribute" class="form-control">
                                        <option value="username"><fmt:message key="administration-users.username" bundle="${lang}"/></option>
                                        <option value="ID">ID</option>
                                        <option value="NID"><fmt:message key="administration-users.nid" bundle="${lang}"/></option>
                                        <option value="surnames"><fmt:message key="administration-users.surnames" bundle="${lang}"/></option>
                                        <option value="role"><fmt:message key="administration-users.role" bundle="${lang}"/></option>
                                        <option value="email"><fmt:message key="administration-users.email" bundle="${lang}"/></option>
                                        <option value="phone number"><fmt:message key="administration-users.phoneNumber" bundle="${lang}"/></option>
                                        <option value="address"><fmt:message key="administration-users.address" bundle="${lang}"/></option>
                                        <option value="country"><fmt:message key="administration-users.country" bundle="${lang}"/></option>
                                        <option value="post code"><fmt:message key="administration-users.postCode" bundle="${lang}"/></option>
                                        <option value="sign up date"><fmt:message key="administration-users.signUpDate" bundle="${lang}"/></option>
                                        <option value="active"><fmt:message key="administration-users.active" bundle="${lang}"/></option>
                                        <option value="deactived since"><fmt:message key="administration-users.deactivedSince" bundle="${lang}"/></option>
                                    </select>
                                </div>

                                <div class="input-group" id="input">
                                    <input type="text" class="form-control" id="searchedFor" name="searchedFor" placeholder="Search for users" value="" size="80">
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-warning"><fmt:message key="administration.search" bundle="${lang}"/></button>
                                    </div>
                                </div>

                                <script type="text/javascript">
                                    function changedSelectValue() {
                                        var select = document.getElementById("selectedUserAttribute");
                                        var selectedOption = select.options[select.selectedIndex].value;

                                        var inputDiv = document.getElementById("input");

                                        if (selectedOption == "post code" || selectedOption == "phone number") {
                                            inputDiv.innerHTML = "";
                                            inputDiv.className = "input-group";

                                            var input = document.createElement('input');
                                            input.type = "number";
                                            input.className = "form-control";
                                            input.id = "searchedFor";
                                            input.name = "searchedFor";
                                            input.placeholder = "Search for users";
                                            input.min = 0;
                                            input.max = 999999999;
                                            input.size = 80;

                                            inputDiv.appendChild(input);

                                            var buttonDiv = document.createElement('div');
                                            buttonDiv.className = "input-group-btn";

                                            var button = document.createElement('button');
                                            button.type = "submit";
                                            button.className = "btn btn-warning";
                                            button.textContent = "Search";

                                            buttonDiv.appendChild(button);
                                            inputDiv.appendChild(buttonDiv);
                                        }

                                        else if (selectedOption == "role") {
                                            inputDiv.innerHTML = "";
                                            inputDiv.className = "input-group";

                                            var selectRole = document.createElement('select');
                                            selectRole.id = "searchedFor";
                                            selectRole.name = "searchedFor";
                                            selectRole.className = "form-control";

                                            var tenantOption = document.createElement('option');
                                            tenantOption.value = "TENANT";
                                            tenantOption.textContent = "Tenant (test)";

                                            var ownerOption = document.createElement('option');
                                            ownerOption.value = "OWNER";
                                            ownerOption.textContent = "Owner (test)";

                                            var administrationOption = document.createElement('option');
                                            administrationOption.value = "ADMINISTRATOR";
                                            administrationOption.textContent = "Administrator (test)";

                                            selectRole.appendChild(tenantOption);
                                            selectRole.appendChild(ownerOption);
                                            selectRole.appendChild(administrationOption);

                                            inputDiv.appendChild(selectRole);

                                            var buttonDiv = document.createElement('div');
                                            buttonDiv.className = "input-group-btn";

                                            var button = document.createElement('button');
                                            button.type = "submit";
                                            button.className = "btn btn-warning";
                                            button.textContent = "Search";

                                            buttonDiv.appendChild(button);
                                            inputDiv.appendChild(buttonDiv);
                                        }

                                        else if (selectedOption == "sign up date" || selectedOption == "deactived since") {
                                            inputDiv.innerHTML = "";
                                            inputDiv.className = "input-group";

                                            var input = document.createElement('input');
                                            input.type = "date";
                                            var text = document.createTextNode("Date: ");
                                            input.id = "searchedFor";
                                            input.name = "searchedFor";
                                            input.className = "form-control";

                                            inputDiv.appendChild(input);

                                            var buttonDiv = document.createElement('div');
                                            buttonDiv.className = "input-group-btn";

                                            var button = document.createElement('button');
                                            button.type = "submit";
                                            button.className = "btn btn-warning";
                                            button.textContent = "Search";

                                            buttonDiv.appendChild(button);
                                            inputDiv.appendChild(buttonDiv);
                                        }

                                        else if (selectedOption == "active") {
                                            inputDiv.innerHTML = "";
                                            inputDiv.className = "input-group";

                                            var input = document.createElement('input');
                                            input.type = "checkbox";
                                            input.name = "active";
                                            input.value = "true";
                                            input.textContent = "Active: ";
                                            input.id = "searchedFor";
                                            input.name = "searchedFor";

                                            inputDiv.appendChild(input);

                                            var buttonDiv = document.createElement('div');
                                            buttonDiv.className = "input-group-btn";

                                            var button = document.createElement('button');
                                            button.type = "submit";
                                            button.className = "btn btn-warning";
                                            button.textContent = "Search";

                                            buttonDiv.appendChild(button);
                                            inputDiv.appendChild(buttonDiv);
                                        }

                                        else {
                                            inputDiv.innerHTML = "";

                                            var input = document.createElement('input');
                                            input.type = "text";
                                            input.className = "form-control";
                                            input.id = "searchedFor";
                                            input.name = "searchedFor";
                                            input.placeholder = "Search for users";
                                            input.size = 80;

                                            inputDiv.appendChild(input);

                                            var buttonDiv = document.createElement('div');
                                            buttonDiv.className = "input-group-btn";

                                            var button = document.createElement('button');
                                            button.type = "submit";
                                            button.className = "btn btn-warning";
                                            button.textContent = "Search";

                                            buttonDiv.appendChild(button);
                                            inputDiv.appendChild(buttonDiv);
                                        }
                                    }
                                </script>

                            </form>

                        </div>

                        <div class="panel-heading"><fmt:message key="administration-users.listOfSearchedUsers" bundle="${lang}"/></div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <tr>
                                        <th>ID</th>
                                        <th><fmt:message key="user.username" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.dni" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.role" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.password" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.name" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.surnames" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.email" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.phone-number" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.address" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.country" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.post-code" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.signup-date" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.active" bundle="${lang}"/></th>
                                        <th><fmt:message key="user.deactivated-since" bundle="${lang}"/></th>
                                        <th><fmt:message key="general.edit" bundle="${lang}"/></th>
                                        <th><fmt:message key="general.delete" bundle="${lang}"/></th>
                                    </tr>

                                    <c:forEach var="user" items="${users}">
                                        <tr>
                                            <td>${user.id}</td>
                                            <td>${user.username}</td>
                                            <td>${user.dni}</td>
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
                                            <td><a href="${pageContext.request.contextPath}/user/delete/${user.id}.html" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span></a></td>
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