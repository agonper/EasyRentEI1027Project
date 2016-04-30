<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="coreActions" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="input" tagdir="/WEB-INF/tags" %>

<tag:paginabasica title="EasyRent - Add a property">

    <jsp:body>

        <h1>New property</h1>
        <form:form method="post" modelAttribute="property" cssClass="form-group">

            <tag:input path="title" label="Name of the property" type="text"/>

            <tag:input path="location" label="Location" type="text"/>

            <tag:input path="location" label="Rooms" type="text"/>

            <tag:input path="capacity" label="Capacity" type="text"/>

            <tag:input path="beds" label="Number of beds" type="text"/>

            <tag:input path="bathrooms" label="Bathrooms" type="text"/>

            <tag:input path="floorSpace" label="Floor space" type="text"/>

            <tag:input path="pricePerDay" label="Initial price per day" type="text"/>

            <form:label path="type" cssClass="control-label col-sm-2">Type of property</form:label>
            <div class="col-sm-10">
                <form:select path="type" cssClass="form-control">
                    <form:options items="${propertyTypes}" itemValue="lowerName" itemLabel="name"/>
                </form:select>
                <form:errors path="type" cssClass=""/>
            </div>

            <tag:input path="description" label="Description" type="textarea"/>
            <input type="submit" value="Add the property" class="btn btn-default"/>
        </form:form>

    </jsp:body>
</tag:paginabasica>