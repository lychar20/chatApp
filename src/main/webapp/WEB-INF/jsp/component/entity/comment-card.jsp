<%@ page contentType="text/html;charset=UTF-8" %>

<div class="main-comment-card w-100">
    <p class="text-center">
        Rédigé le ${dateUtils.getDateFormat(comment.createdAt, "dd/MM/yyyy")}
        par <a class="btn-link" href="">${comment.chatter.nickname}</a>
    </p>
    <figcaption class="blockquote-footer text-center">
        <c:if test="${not empty comment.moderator}">
            Modéré par <cite title="Source Title">${comment.moderator.nickname}</cite> -
            le ${dateUtils.getDateFormat(review.moderatedAt, "dd/MM/yyyy")}
        </c:if>
        <c:if test="${empty comment.moderator}">

            <div class="d-flex">


            <security:authorize access="hasRole('MODERATOR')">
            <cite title="Source Title">En attente de moderation ⌛</cite>
            </security:authorize>

            <c:if test="${UrlRoute.URL_FORUM_COMMENT_MODERATE}/${id}">
                <a class="btn btn-link rating-5"
                   href=""
                   title="Refuser"
                >
                    <i class="fa-solid fa-xmark fa-2x"></i>
                </a>
            </c:if>
            </div>
        </c:if>
    </figcaption>
    <div class="comment-card w-100">
        <p class="comment-description">

        <a href="">
            ${jspUtils.excerpt(comment.content, 209)}
        </a>

        </p>
        <div class="d-flex justify-content-between">
            <p class="">
            nombre de responses

            </p>
            <a class="btn-link" href="">

            </a>
        </div>
    </div>
</div>