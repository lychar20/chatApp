<!-- <%@ include file="../../jsp/tag.jsp" %> -->
<jsp:include flush="true" page="../base.jsp"/>




<div class="container">

<h2>Choissisez votre de fil de de discussoon</h2>

    <div class="row">
        <c:forEach items="${pageReviews.content}" var="review">
            <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
                <%@ include file="component/entity/review-card.jsp" %>
            </div>
        </c:forEach>
    </div>


</div>






<%@ include file="../../jsp/footer.jsp" %>