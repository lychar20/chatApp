<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "ChatAppLogin";
    }
    request.setAttribute("title", title);
%>

<html>
<head>
    <title>${title}</title>
    <link href="${contextPath}/css/main.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <script type="text/javascript" src="${contextPath}/js/init-sortable.js"></script>
    <script type="text/javascript" src="${contextPath}/js/hide-form.js"></script>
    <script type="text/javascript" src="${contextPath}/js/alert.js"></script>
    <script type="text/javascript" src="${contextPath}/js/multiple-select.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/main.js"></script>
</head>
    <body>

    <security:authorize access="isAuthenticated()">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="row w-100">
            <div class="col-3">
                <a class="navbar-brand ms-3" href="${contextPath}/">
                    <i class="fa-brands fa-steam fa-3x"></i>
                </a>
            </div>
            <div class="col-5">
                <div class="main-container p-2">
                    <div class="d-flex">
                        <input type="text"
                               class="form-control"
                               placeholder="Starcraft, FPS, ..."
                               data-search-bar-games
                        >
                        <a class="my-auto me-3">
                            <i class="fa fa-magnifying-glass"></i>
                        </a>
                    </div>
                    <div class="search-response-container">
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div class="d-flex justify-content-end">
                            <span class="ms-2">
                                Bienvenue
                                <a class="logged-user btn-link" href="">
                                    ${userLogged.nickname}
                                    <security:authorize access="hasRole('MODERATOR')">
                                        <i class="fa-solid fa-user-graduate"></i>
                                    </security:authorize>
                                </a>
                            </span>
                </div>
                <div class="d-flex justify-content-end">
                    <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                        <button type="submit" tabindex="3" class="btn btn-link" title="Se déconnecter">
                            <i class="fa-solid fa-right-from-bracket"></i>
                        </button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
            </div>
        </div>
    </nav>
    </security:authorize>
    <c:if test="${not empty flashMessage.message}">
    <div class="container">
        <div class="alert alert-${flashMessage.type}">
                ${flashMessage.message}
        </div>
    </div>
    </c:if>

    <%-- Navbarici : SI NECESSAIRE --%>