<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:paginabasica title="Añadir usuario">
    <jsp:body>
        <h1>Añadir usuario</h1>
        <form:form cssClass="form-horizontal" method="post" modelAttribute="user">
            <t:input path="username" required="true"/>
            <t:input path="dni" label="DNI"/>
            <t:input path="password" type="password" required="true"/>
            <t:input path="name" required="true"/>
            <t:input path="surnames" required="true"/>
            <t:input path="email" required="true"/>
            <t:input path="phoneNumber" label="Phone number"/>
            <t:input path="postalAddress" label="Address"/>
            <t:input path="country" />
            <t:input path="postCode" label="Post code"/>
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Añadir</button>
        </form:form>
    </jsp:body>
</t:paginabasica>
