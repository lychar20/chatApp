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
    <script type="text/javascript" src="${contextPath}/js/oneToOne.js"></script>
</head>
    <body class="">

    <security:authorize access="isAuthenticated()">
    <nav class=" cc-navbar navbar navbar-expand-lg navbar-dark  justify-content-around">
        <div class="row w-100 py-3">
            <div class="col-7">
                <a class="navbar-brand ms-3" href="${contextPath}/">
                    <i class="fa-solid fa-hand-holding-heart fa-3x"></i>
                </a>
            </div>
            <!--<div class="col-5">
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
            </div> -->
            <div class="col-4 d-flex justify-content-end ms-5">

                            <span class="ms-2 text-greeting">
                                Bienvenue
                                <a class="logged-user btn-link" href="">
                                    ${userLogged.nickname}
                                    <security:authorize access="hasRole('MODERATOR')">
                                        <i class="fa-solid fa-user-graduate"></i>
                                    </security:authorize>
                                </a>
                            </span>


                    <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                        <button type="submit" tabindex="3" class="btn btn-link" title="Se déconnecter">
                            <i class="fa-solid fa-person-walking-arrow-right fa-2x"></i>
                        </button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>

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