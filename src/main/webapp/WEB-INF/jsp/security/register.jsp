<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
  <f:form method="POST" modelAttribute="registerDto" class="form-signin">
    <h2 class="form-signin-heading">Create your account</h2>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="text" path="nickname" class="form-control" placeholder="Nickname"
                    autofocus="true"/>
        <f:errors path="nickname" cssClass="invalid-feedback"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="text" path="email" class="form-control" placeholder="Email"
                    autofocus="true"/>
        <f:errors path="email" cssClass="invalid-feedback"/>
      </div>
    <div class="form-group ${status.error ? 'has-error' : ''}">
      <f:input type="date" path="birthAt" class="form-control" placeholder="Date de naissance"
               autofocus="true"/>
      <f:errors path="birthAt" cssClass="invalid-feedback"/>
    </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="password" path="password" class="form-control" placeholder="Password"/>
        <f:errors path="password" cssClass="invalid-feedback"/>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  </f:form>
</div>

<%@ include file="../footer.jsp" %>