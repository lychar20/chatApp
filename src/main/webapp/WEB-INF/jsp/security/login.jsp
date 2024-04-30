<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="testons mw-100 h-100">
<div class="container first_page_background">
  <form method="POST" action="${UrlRoute.URL_LOGIN}" class="form-signin p-5">
    <h2 class="form-heading text-center padding-login">Log in</h2>
    <div class="form-group ${error != null ? 'has-error' : ''}">
      <input name="username" type="text" class="form-control w-50 form_center  " placeholder="Username"
             autofocus="true"/>
      <input name="password" type="password" class="form-control w-50 form_center " placeholder="Password"/>
      <p class="invalid-feedback">${error}</p>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <button class="btn btn-lg btn-primary btn-block w-50 form_center" type="submit">Log In</button>
      <h4 class="text-center mt-3">
        <a href="${contextPath}/register" class="btn-link  ">
          Create an account
        </a>
      </h4>
    </div>
  </form>
</div>

<%@ include file="../footer.jsp" %>
</div>