<!-- <%@ include file="../../jsp/tag.jsp" %> -->
<jsp:include flush="true" page="../base.jsp"/>



<h1>Bienvenue sur le forum</h1>


<h2> Voyez les différents fils de discussion existant testmmmm</h2>


<!-- pour pouvoir créer un fil de discussion -->

<div class="container-login">
    <f:form method="POST" modelAttribute="threadDTO" class="form-signin">
        <h1 class=" login  form-signin-heading">Créez votre fil</h1>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="title" class="form-control w-25" placeholder="Title"
                     autofocus="true"/>
            <f:errors path="title" cssClass="invalid-feedback"/>
        </div>


      <!--  <div class="col-md-6 col-sm-12 ">
            <f:label class="col-form-label" path="category">
                Category
            </f:label>
            <input class="form-control" data-multiple-select-input="category" />
            <f:select path="category"
                      multiple="multiple"
                      items="${categories}"
                      cssClass="form-select"
                      itemLabel="name"
                      data-multiple-select="category"
            >
            </f:select>
            <f:errors path="category" cssClass="invalid-feedback"/>
        </div> -->



        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </f:form>
</div>



<%@ include file="../../jsp/footer.jsp" %>
