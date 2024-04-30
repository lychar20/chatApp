<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="???????????"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<div class="cc-background1">
<div class="container h-50 p-5">
    <h1 class="cc-padding titleChat text-center merriwheather">Choissisez votre channel de discussion</h1>
</div>


<div class="row d-flex d-flex justify-content-around  ">
        <a class="cc-text-link col-lg-3 col-md-6 col-sm-12 mt-4 border border-1 p-3" href="${UrlRoute.URL_SALON_CHAT}">
        <div class=" boxChoice  text-center fs-1 fst-italic redressed" title="Discutez en direct avec des gens">
            Chat
        </div>
        </a>

    <a class="cc-text-link col-lg-3 col-md-6 col-sm-12 mt-4 border border-1 p-3" href="${UrlRoute.URL_FORUM}">
    <div class=" boxChoice  text-center fs-1 fst-italic redressed" title="Ecrivez votre message dans le groupe dédié">
        Forum
    </div>
    </a>

</div>


</div>





<%@ include file="footer.jsp" %>
