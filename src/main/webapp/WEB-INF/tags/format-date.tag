<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="value" type="java.util.Date" %>
<fmt:formatDate value="${value}" pattern="dd/MM/yyyy"/>