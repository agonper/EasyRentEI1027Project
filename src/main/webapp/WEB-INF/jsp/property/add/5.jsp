<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="navs" tagdir="/WEB-INF/tags/navs" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:message key="add-property.title" bundle="${lang}" var="title"/>
<fmt:message key="property.photos" bundle="${lang}" var="subtitle"/>
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
                <div id="photo-list" class="row center-block">
                    <c:forEach items="${propertyPhotos}" var="pictureUri" varStatus="status">
                        <div id="${pictureUri}" class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            <div class="thumbnail">
                                <img class="img-responsive" src="${pageContext.request.contextPath}/uploads/property-pics/${pictureUri}">
                                <div class="caption text-right">
                                    <p><button id="remove-${pictureUri}" class="btn btn-danger property-photo-remove">
                                        <span class="glyphicon glyphicon-remove"></span> <fmt:message key="general.remove" bundle="${lang}"/>
                                    </button></p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <br>

                <form id="property-pictures" action="${pageContext.request.contextPath}/property/0/upload-photos" class="dropzone">
                    <div class="fallback">
                        <input name="file" type="file" multiple />
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="type" value="session">
                </form>
                <br>
                <form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/property/add/5">
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

        <fmt:message key="property.upload-photos" bundle="${lang}" var="uploadPlaceholder"/>
        <fmt:message key="property.remove-picture" bundle="${lang}" var="removePicture"/>
        <fmt:message key="property.cancel-upload" bundle="${lang}" var="cancelUpload"/>
        <script>
            (function () {
                var postOptions = {
                    type: 'session'
                };
                postOptions['${_csrf.parameterName}'] = '${_csrf.token}';

                Dropzone.options.propertyPictures = {
                    addRemoveLinks: true,
                    dictDefaultMessage: '${uploadPlaceholder}',
                    dictRemoveFile: '${removePicture}',
                    dictCancelUpload: '${cancelUpload}',
                    init: function () {
                        var _this = this;
                        _this.on('complete', function (file) {
                            if (file.xhr.status == 200){
                                var pictureId = file.xhr.response;
                                $('#photo-list').append(''+
                                        '<div id="' + pictureId + '" class="col-lg-3 col-md-4 col-sm-6 col-xs-12">' +
                                            '<div class="thumbnail">' +
                                                '<img class="img-responsive" src="${pageContext.request.contextPath}/uploads/property-pics/' + pictureId + '">' +
                                                '<div class="caption text-right">' +
                                                    '<p><button id="remove-' + pictureId + '" class="btn btn-danger property-photo-remove">' +
                                                        '<span class="glyphicon glyphicon-remove"></span> <fmt:message key="general.remove" bundle="${lang}"/>' +
                                                    '</button></p>' +
                                                '</div>' +
                                            '</div>' +
                                        '</div>');
                                $('#remove-' + pictureId).on('click', removeHandler);
                                $(file.previewElement).find('.dz-remove').on('click', function () {

                                    $.post('${pageContext.request.contextPath}/uploads/property-pics/' + file.xhr.response + '/remove', postOptions).done(function () {
                                        $('#' + pictureId).remove();
                                    });
                                });
                            }
                        });
                    }
                };
                
                function removeHandler(evt) {
                    evt.stopPropagation();
                    var resourceId = $(evt.currentTarget).attr('id').split('-')[1];
                    $.post('${pageContext.request.contextPath}/uploads/property-pics/' + resourceId + '/remove', postOptions).done(function () {
                        $('#' + resourceId).remove();
                    });
                }

                $('.property-photo-remove').on('click', removeHandler);
            })();
        </script>
    </jsp:body>
</tag:paginabasica>