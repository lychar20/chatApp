<!-- <%@ include file="../../jsp/tag.jsp" %> -->
<jsp:include flush="true" page="../base.jsp"/>


<div class="container-login">
    <f:form method="POST" modelAttribute="threadDTO" class="form-signin">
        <h1 class=" login text-center form-signin-heading">Créer votre fil</h1>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="title" class="form-control" placeholder="Title"
                     autofocus="true"/>
            <f:errors path="title" cssClass="invalid-feedback"/>
        </div>


        <div class="col-md-6 col-sm-12 ">
            <f:label class="col-form-label" path="categories">
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
        </div>
        </div>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </f:form>
</div>



<%@ include file="../../jsp/footer.jsp" %>