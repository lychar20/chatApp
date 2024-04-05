<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<%-- Importe l'objet UrlRoute pour pouvoir l'utiliser partout dans les JSP --%>
<%@ page import="fr.charly.chatApp.mapping.UrlRoute" %>

<%-- Imports nécessaires pour récupérer le UserService dans les JSP --%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>

<%@ page import="fr.charly.chatApp.utils.DateUtils" %>

<%@ page import="org.springframework.http.server.ServletServerHttpRequest" %>
<%@ page import="org.springframework.web.util.UriComponentsBuilder" %>
<%@ page import="fr.charly.chatApp.service.UserService" %>
<%@ page import="fr.charly.chatApp.entity.User" %>

<%--&lt;%&ndash; Renomme de manière plus simple le "pageContext.request.contextPath" &ndash;%&gt;--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    String path = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
    Object query = request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
    String queryString = "";
    if (query != null) {
        queryString = query.toString();
    }
    request.setAttribute("currentQuery", queryString);
    request.setAttribute("currentPath", path);
    request.setAttribute("currentUrl", url + path);

    WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(request);
   if (ctx != null) {
           UserService userService = ctx.getBean(UserService.class);
           if (request.getUserPrincipal() != null) {
               User user = userService.findByNickname(request.getUserPrincipal().getName());
               request.setAttribute("userLogged", user);
           }
            request.setAttribute("jspUtils", ctx.getBean(JspUtils.class));
   }


%>