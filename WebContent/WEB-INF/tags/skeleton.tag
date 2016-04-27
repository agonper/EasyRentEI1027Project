<%@ tag description="Skeleton web page, it includes de header and the footer"
        pageEncoding="UTF-8"%>

<%@ attribute name="title" required="false" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.css"
              rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"
               rel="stylesheet">
        <!-- Other styles -->



    </head>

    <body>

    </body>
</html>