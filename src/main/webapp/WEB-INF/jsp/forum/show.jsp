<!-- <%@ include file="../../jsp/tag.jsp" %> -->
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include flush="true" page="../base.jsp"/>



<h1>Bienvenue sur le forum ${category.name} </h1>


<h2> Voyez les différents fils de discussion existant testmmmm</h2>



<div class="row h-100 justify-content-center align-items-center">
    <c:forEach items="${category.threads}" var="thread">
        <div class=" choiceChat col-lg-4 col-md-6 col-sm-12 mt-4 ">
            <%@ include file="../component/entity/thread-card.jsp" %>
        </div>
    </c:forEach>
</div>



<!-- pour pouvoir créer un fil de discussion -->

<div class="container-login">
    <f:form method="POST" modelAttribute="threadDTO" class="form-signin">
        <h1 class=" login  form-signin-heading">Créez votre fil</h1>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="title" class="form-control w-25" placeholder="Title"
                     autofocus="true"/>
            <f:errors path="title" cssClass="invalid-feedback"/>
        </div>




        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </f:form>
</div>



<%@ include file="../../jsp/footer.jsp" %>
