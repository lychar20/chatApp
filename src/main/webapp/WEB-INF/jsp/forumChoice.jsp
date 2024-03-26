<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="???????????"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container ">
    <h1 class="text-center">Choissisez votre Forum de discussion</h1>
</div>

<div class="row h-100 justify-content-center align-items-center">
    <c:forEach items="${categoryChoice}" var="category">
        <div class=" choiceChat col-lg-4 col-md-6 col-sm-12 mt-4 ">
            <%@ include file="component/entity/forum-discussion-card.jsp" %>
        </div>
    </c:forEach>
</div>



<%@ include file="footer.jsp" %>