<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="coreActions" uri="http://java.sun.com/jsp/jstl/core"%>

<tag:skeleton title="List properties">
    <jsp:body>
        <h1>List of properties</h1>
        <table class="">
            <tr>
                <td>id</td>
                <td>Owner id</td>
                <td>Title</td>
                <td>Location</td>
                <td>Rooms</td>
                <td>Capacity</td>
                <td>Beds</td>
                <td>Bathrooms</td>
                <td>Floor space</td>
                <td>Price per day</td>
                <td>Creation date</td>
                <td>Type</td>
                <td>Description</td>
            </tr>
            <coreActions:forEach items="${properties}" var="property">
                <tr>
                    <td>${property.id}</td>
                    <td>${property.ownerID}</td>
                    <td>${property.title}</td>
                    <td>${property.location}</td>
                    <td>${property.rooms}</td>
                    <td>${property.capacity}</td>
                    <td>${property.beds}</td>
                    <td>${property.bathrooms}</td>
                    <td>${property.floorSpace}</td>
                    <td>${property.pricePerDay}</td>
                    <td>${property.creationDate}</td>
                    <td>${property.type}</td>
                    <td>${property.description}</td>
                    <td><a href="update/${property.id}.html" class="">Update data</a></td>
                    <td><a href="delete/${property.id}.html" class="">Delete property</a></td>
                </tr>
            </coreActions:forEach>
        </table>

        <a href="../index.jsp">Go back to the index page</a>
    </jsp:body>
</tag:skeleton>