<!-- <%@ include file="../../jsp/tag.jsp" %> -->
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include flush="true" page="../base.jsp"/>


<div class="first-page ">
<h1 class="text-center">Bienvenue sur le forum ${category.name} </h1>


<h2 class="text-center mt-5"> Voici différents fils de discussion existants </h2>



<div class="row h-100 justify-content-center align-items-center">
    <c:forEach items="${category.threads}" var="thread">
        <div class=" choiceChat col-lg-4 col-md-6 col-sm-12 mt-4 ">
            <%@ include file="../component/entity/thread-card.jsp" %>
        </div>
    </c:forEach>
</div>



<!-- pour pouvoir créer un fil de discussion -->

<div class="container-login ">
    <f:form method="POST" modelAttribute="threadDTO" class="form-signin">
        <h3 class=" login  form-signin-heading">Créez votre fil</h3>
        <div class=" align-self-center form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="title" class="form-control w-25" placeholder="Title"
                     autofocus="true"/>
            <f:errors path="title" cssClass="invalid-feedback"/>
        </div>




        <button class="btn btn-lg btn-primary btn-block w-25" type="submit">Submit</button>
    </f:form>
</div>


</div>
<%@ include file="../../jsp/footer.jsp" %>
