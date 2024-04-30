<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../jsp/tag.jsp" %>
<c:set var="title" scope="request" value="Comments"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class=" cc-background5 px-5">

<h1 class="p-5 merriwheather"> Voici les messages liés au fil de discussion choisi</h1>


 <h3 class=" my-5 merriwheather fs-3 fst-italic">Les Messages </h3>

                 <button class="ms-2 btn btn-link"
                         title="Ajouter un message"
                         data-hide-show-button="formComment"
                 >
                     <i class="fa fa-pen fa-2x"></i>
                 </button>


    <div class="my-3 d-none"
                  data-hide-show-container="formComment"
             >
                 <f:form cssClass="col-md-8 col-sm-12 mx-auto"
                         action="${currentUrl}"
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


        <security:authorize access="hasRole('MODERATOR')">

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
        </security:authorize>

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

                          <div class="my-3 d-none"
                                                   data-hide-show-container="responseComment${comment.id}"
                                              >
                                                  <f:form cssClass="col-md-8 col-sm-12 mx-auto"
                                                          action="${currentUrl}/${comment.id}"
                                                          method="post"
                                                          modelAttribute="commentDTO"
                                                  >
                                                      <div class="mb-3 row">
                                                          <f:label path="description" class="col-sm-2 col-form-label ">Description</f:label>
                                                          <div class="col-sm-10 w-50">
                                                              <f:textarea cssClass="form-control" path="description"/>
                                                              <f:errors path="description" cssClass="invalid-feedback"/>
                                                          </div>
                                                      </div>

                                                      <f:button type="submit" class="btn btn-success">
                                                          <i class="fa fa-check"></i> Ajouter
                                                      </f:button>
                                                  </f:form>
                                              </div>


                    </c:forEach>
                </div>


</div>


<%@ include file="../../jsp/footer.jsp" %>