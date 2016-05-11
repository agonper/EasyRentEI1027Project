<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ tag description="Input tag with label and error control" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="label" required="false" %>
<%@ attribute name="type" required="false" %>
<%@ attribute name="required" required="false" type="java.lang.Boolean" %>

<c:if test="${empty label}">
    <c:set var="label" value="${fn:toUpperCase(fn:substring(path, 0, 1))}${fn:substring(path, 1, fn:length(path))}"/>
</c:if>
<c:if test="${empty type}">
    <c:set var="type" value="${(empty type) ? 'text' : type}"/>
</c:if>
<spring:bind path="${path}">
    <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:label path="${path}" cssClass="control-label col-sm-2">${label}<c:if test="${required}"><span class="text-info">*</span></c:if></form:label>
        <div class="col-sm-10">
            <c:if test='${type.equals("text")}'><form:input path="${path}" cssClass="form-control" /></c:if>
            <c:if test='${type.equals("email")}'><div class="input-group"><span class="input-group-addon">@</span><form:input path="${path}" cssClass="form-control" /></div></c:if>
            <c:if test='${type.equals("password")}'><form:password path="${path}" cssClass="form-control" /></c:if>
            <c:if test='${type.equals("textarea")}'><form:textarea path="${path}" cssClass="form-control" /></c:if>
            <c:if test="${status.error}">
                <span class="help-block">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>
</spring:bind>