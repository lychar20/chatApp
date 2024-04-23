<%@ page contentType="text/html;charset=UTF-8" %>





<div class="main-comment-card w-100">
    <p class="text-center">
        Rédigé le ${dateUtils.getDateFormat(comment.createdAt, "dd/MM/yyyy")}
        par <a class="btn-link" href="">${comment.chatter.nickname}</a>
    </p>
    <figcaption class="blockquote-footer text-center">
        <c:if test="${not empty comment.moderator}">
            Modéré par <cite title="Source Title">${comment.moderator.nickname}</cite> -
            le ${dateUtils.getDateFormat(comment.moderatedAt, "dd/MM/yyyy")}
        </c:if>
        <c:if test="${empty comment.moderator}">

            <div class="d-flex">


            <security:authorize access="hasRole('MODERATOR')">

            <cite title="Source Title">En attente de moderation ⌛</cite>


                            <a class="btn btn-link rating-5"
                               href="${UrlRoute.URL_FORUM}/${thread.category.slug}/${thread.slug}/moderate/${comment.id}"
                               title="Refuser"
                            >
                                <i class="fa-solid fa-xmark fa-2x"></i>
                            </a>


            </security:authorize>


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
                <a href="${UrlRoute.URL_FORUM}/${thread.category.slug}/${thread.slug}/responses/${comment.id}">
            ${comment.responses.stream().filter(c -> c.moderator == null).count()} réponses
                </a>
            </p>


          <button class="ms-2 btn btn-link"
                                              title="Répondre"
                                              data-hide-show-button="responseComment${comment.id}"
                                      >
                                          <i class="fa fa-pen fa-2x"></i>
                     </button>


                      <div class="my-3 d-none"
                           data-hide-show-container="responseComment${comment.id}"
                      >
                          <f:form cssClass="col-md-8 col-sm-12 mx-auto"
                                  action="${currentUrl}/${comment.id}"
                                  method="post"
                                  modelAttribute="commentDTO"
                          >
                              <div class="mb-3 row">
                                  <f:label path="description" class="col-sm-2 col-form-label">Description</f:label>
                                  <div class="col-sm-10">
                                      <f:textarea cssClass="form-control" path="description"/>
                                      <f:errors path="description" cssClass="invalid-feedback"/>
                                  </div>
                              </div>

                              <f:button type="submit" class="btn btn-success">
                                  <i class="fa fa-check"></i> Ajouter
                              </f:button>
                          </f:form>
                      </div>






        </div>
    </div>
</div>