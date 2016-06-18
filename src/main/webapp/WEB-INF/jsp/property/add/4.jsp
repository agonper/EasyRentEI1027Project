<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="er" uri="/WEB-INF/easy-rent.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="navs" tagdir="/WEB-INF/tags/navs" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:message key="add-property.title" bundle="${lang}" var="title"/>
<fmt:message key="property.services" bundle="${lang}" var="subtitle"/>
<tag:paginabasica title="${title}: subtitle">
    <jsp:body>
        <tag:property-add-breadcrumb subtitle="${subtitle}"/>
        <div class="page-header">
            <h1>${title} <small>${subtitle}</small></h1>
        </div>

        <navs:stepper step="${pageContext.session.getAttribute('addPropertyMap').step.ordinal()}" steps="${steps}" path="/property/add"/>

        <div class="panel panel-warning">
            <div class="panel-heading">
                    ${subtitle}
            </div>
            <div class="panel-body">
                <div id="property-services">
                    <c:forEach items="${services}" var="service" varStatus="status">
                        <er:color-gen number="${status.index}" var="color"/>
                        <button style="margin: 3px;" id="service-${status.index}" class="btn btn-labeled btn-${color} remove-service">
                        <span class="btn-label">
                            <i class="glyphicon glyphicon-remove"></i>
                        </span>
                            ${service.name}
                        </button>
                    </c:forEach>
                </div>
                <hr>
                <form id="upload-service" class="form-inline" action="${pageContext.request.contextPath}/service/property/0/add">
                    <div class="form-group">
                        <label><fmt:message key="service.title" bundle="${lang}"/> </label>
                        <div class="input-group">
                            <input name="name" class="form-control" required>
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-plus"></span> <fmt:message key="general.add" bundle="${lang}"/></button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="type" value="session"/>
                </form>
                <br>
                <form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/property/add/4">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="row">
                        <div class="col-sm-offset-1 col-sm-10">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/property/add?step=3"><span class="glyphicon glyphicon-backward"></span> <fmt:message key="general.back" bundle="${lang}"/></a>
                            <button type="submit" class="btn btn-warning"><fmt:message key="general.next" bundle="${lang}"/> <span class="glyphicon glyphicon-forward"></span> </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>
            (function () {
                $('#upload-service').submit(function (evt) {
                    evt.preventDefault();
                    var form = $(this);
                    var data = form.serializeArray();

                    var options = {};
                    data.forEach(function (elem) {
                        options[elem.name] = elem.value;
                    });
                    $.post(form.attr('action'), options).done(function () {
                        reloadServices();
                        form.trigger('reset');
                    })

                });

                $('.remove-service').on('click', removeService);

                function removeService() {
                    var $el = $(this);
                    var id = $el.attr('id').split('-')[1];

                    var options = {
                        type: 'session'
                    };
                    options['${_csrf.parameterName}'] = '${_csrf.token}';

                    $.post('${pageContext.request.contextPath}/service/property/0/remove/' + id, options).done(reloadServices);
                }

                function reloadServices() {
                    var colors = ["primary", "success", "info", "warning", "danger"];
                    $.getJSON('${pageContext.request.contextPath}/service/property/0/list?type=session').done(function (services) {
                        var propertyServices = $('#property-services');
                        propertyServices.html("");

                        services.forEach(function (service, i) {
                            propertyServices.append('' +
                                    '<button style="margin: 3px;" id="service-' + i + '" class="btn btn-labeled btn-' + colors[i % colors.length] + ' remove-service">' +
                                        '<span class="btn-label">' +
                                            '<i class="glyphicon glyphicon-remove"></i>' +
                                        '</span>' +
                                        service.name +
                                    '</button>');
                        });
                        $('.remove-service').on('click', removeService);
                    });
                }
            })();
        </script>
    </jsp:body>
</tag:paginabasica>