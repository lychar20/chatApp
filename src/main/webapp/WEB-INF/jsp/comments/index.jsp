<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../jsp/tag.jsp" %>
<c:set var="title" scope="request" value="Comments"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1> Voici les messages liés aux thread choisi</h1>


 <h3 class=" my-5">Les Messages
            <a href="${UrlRoute.URL_FORUM_NAME_COMMENTS}" class="ms-2" title="Ajouter un message">
                <i class="fa fa-circle-plus link-green"></i>
            </a>

    </h3>


 <div class="d-flex justify-content-between">
        <div class="d-flex">
            <!-- Label à afficher -->
            <c:set var="label" scope="request" value="Date"/>
            <!-- Sur quelle propriété de l'objet on souhaite trier -->
            <c:set var="sortable" value="createdAt"/>
            <%@ include file="../../jsp/component/sortable.jsp" %>


            <c:set var="label" scope="request" value="Chatter"/>
            <c:set var="sortable" value="chatter.nickname"/>
            <%@ include file="../../jsp/component/sortable.jsp" %>


                <div class="sort-filter mt-4 me-3">
                <select class="form-select sortable-select">
                    <option value="all" data-filter-url="${currentUrl}">
                        Tous les commentaires
                    </option>
                    <option value="sort=moderator,desc"
                            data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,desc")}"
                    >
                        Modérés
                    </option>
                    <option value="sort=moderator,asc"
                            data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,asc")}"
                    >
                        À modérer
                    </option>
                </select>
            </div>


            <%@ include file="../../jsp/component/filter-reset.jsp" %>
        </div>

        <c:set var="page" scope="request" value="${pageComments}"/>
                <%@ include file="../../jsp/component/pagination-number.jsp" %>
            </div>

            <div class="row">
                    <c:forEach items="${pageComments.content}" var="comment">
                        <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
                            <%@ include file="../../jsp/component/entity/comment-card.jsp" %>
                        </div>
                    </c:forEach>
                </div>

<%@ include file="../../jsp/footer.jsp" %>