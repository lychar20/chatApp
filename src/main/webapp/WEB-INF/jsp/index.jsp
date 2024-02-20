<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="???????????"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container">
    <h1>Choissisez vos sujets de discussion</h1>
</div>


<div class="row">
    <c:forEach items="${categoryChoice}" var="category">
        <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
            <%@ include file="component/entity/discussion-card.jsp" %>
        </div>
    </c:forEach>
</div>
<security:authorize access="hasRole('MODERATOR')">
    <div>
        <a href="${UrlRoute.URL_EXPORT}" class="btn btn-link">
            <i class="fa-solid fa-file-excel me-1"></i>
            Télécharger export Excel
        </a>
    </div>
</security:authorize>




<%@ include file="footer.jsp" %>
