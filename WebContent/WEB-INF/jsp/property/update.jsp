<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:paginabasica title="EasyRent - Update the property">
    <jsp:body>

        <h1>Updating the property</h1>
        <form:form method="post" modelAttribute="property" cssClass="form-group">

            <form:label path="title" cssClass="">Name of the property</form:label>
            <form:input path="title" cssClass="form-control"></form:input>
            <form:errors path="title" cssClass="error"></form:errors>

            <form:label path="location" cssClass="">Location</form:label>
            <form:input path="location" cssClass="form-control"></form:input>
            <form:errors path="location" cssClass=""></form:errors>

            <form:label path="rooms" cssClass="">Rooms</form:label>
            <form:input path="rooms" cssClass="form-control"></form:input>
            <form:errors path="rooms" cssClass=""></form:errors>

            <form:label path="capacity" cssClass="">Capacity</form:label>
            <form:input path="capacity" cssClass="form-control"></form:input>
            <form:errors path="capacity" cssClass=""></form:errors>

            <form:label path="beds" cssClass="">Number of beds</form:label>
            <form:input path="beds" cssClass="form-control"></form:input>
            <form:errors path="beds" cssClass=""></form:errors>

            <form:label path="bathrooms" cssClass="">Bathrooms</form:label>
            <form:input path="bathrooms" cssClass="form-control"></form:input>
            <form:errors path="bathrooms" cssClass=""></form:errors>

            <form:label path="floorSpace" cssClass="">Floor space</form:label>
            <form:input path="floorSpace" cssClass="form-control"></form:input>
            <form:errors path="floorSpace" cssClass=""></form:errors>

            <form:label path="pricePerDay" cssClass="">Initial price per day</form:label>
            <form:input path="pricePerDay" cssClass="form-control"></form:input>
            <form:errors path="pricePerDay" cssClass=""></form:errors>

            <form:label path="type" cssClass="">Type of property</form:label>
            <form:select path="type" cssClass="form-control">
                <form:options items="${propertyTypes}" itemValue="lowerName" itemLabel="name"/>
            </form:select>
            <form:errors path="type" cssClass=""/>

            <form:label path="description" cssClass="">Description</form:label>
            <form:input path="description" cssClass="form-control"></form:input>
            <form:errors path="description" cssClass=""></form:errors>

            <input type="submit" value="Update the property" class="btn btn-default"/>
        </form:form>

    </jsp:body>
</tag:paginabasica>