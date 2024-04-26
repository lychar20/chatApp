<!-- <%@ include file="../../jsp/tag.jsp" %> -->
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include flush="true" page="../base.jsp"/>



<h1 class=" mt-5 fs-1 fst-italic text-primary"> ${jspUtils.excerpt(comment.content, 209)} </h1>

<h2 class="mt-5"> Toutes les reponses  </h2>

<div>

 <c:set var="page" scope="request" value="${pageComments}"/>
                <%@ include file="../component/pagination-number.jsp" %>
            </div>

            <div class="row">
                    <c:forEach items="${pageComments.content}" var="comment">
                        <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
                            <%@ include file="../component/entity/comment-card.jsp" %>
                        </div>



                    </c:forEach>
                </div>


</div>



<button class="mt-5 ms-2 btn btn-link"
                                              title="Répondre au méssage initial"
                                              data-hide-show-button="responseComment${comment.id}"
                                      >
                                          <i class="fa-solid fa-comment fa-3x"></i>
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






<%@ include file="../../jsp/footer.jsp" %>