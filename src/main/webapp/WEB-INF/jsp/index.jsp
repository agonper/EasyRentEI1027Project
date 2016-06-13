<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tag:paginabasica title="EasyRent" resource="index">
    <jsp:body>
        <div class="page-header">
            <h1><fmt:message key="easyrent.home" bundle="${lang}"/></h1>
        </div>
        <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi elementum ex a neque tempor iaculis. In vel sollicitudin massa. Phasellus posuere ac nulla in volutpat. Etiam sit amet vestibulum velit, ut scelerisque nisi. Fusce risus quam, feugiat egestas dui vitae, facilisis molestie sapien. Vestibulum pellentesque semper sem, id consectetur odio feugiat ac. Quisque tincidunt nunc in mi venenatis vehicula nec nec massa. Sed nec elit condimentum magna ultrices fermentum. Fusce laoreet risus tortor, eu commodo lacus ullamcorper sit amet. Vestibulum dictum non eros a fermentum. Fusce blandit laoreet lacus, eget posuere lectus mollis nec. Praesent malesuada enim arcu. Donec at faucibus tellus, sed mattis arcu. Ut convallis felis nisl, nec maximus nibh congue ac.
        </p>

        <h2><fmt:message key="easyrent.action-list" bundle="${lang}"/></h2>

        <div class="row text-center">
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/user/list.html">User list</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/property/list.html">Listar propiedades</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/property/add.html">Introducir propiedad</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/photo/add.html">Introducir foto</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/property/listOwnProperties.html">Listar propias propiedades</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/property/availabilityPeriod/listAll.html">Listar todos los periodos de disponibilidad</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/service/list.html">Listar todos los servicios</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/service/add.html">Añadir nuevo servicio</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/user/bookingProposal/list.html">Listar bkProposals propias (si se es admin todas)</a></div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/administration">Entrar en el panel de administración</a></div>
        </div>
        <div class="well">
            <p>Está feo! Arreglar!</p>
        </div>
    </jsp:body>
</tag:paginabasica>
