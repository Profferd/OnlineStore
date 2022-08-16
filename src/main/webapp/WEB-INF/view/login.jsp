<%--
  Created by IntelliJ IDEA.
  User: dgrus
  Date: 6/21/2022
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Log In</title>
    <jsp:include page="base/headerLink.jsp"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources.textResources" var="local"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col"></div>
        <div class="col-6 my-sm-3">
            <c:if test="${sessionScope.user != null}">
                <div class="alert alert-danger fade show " role="alert">
                    <fmt:message bundle="${local}" key="Logged.In"/>
                </div>
            </c:if>
            <c:if test="${sessionScope.user == null}">

                <h1 class="mt-5 fw-bold"><fmt:message bundle="${local}" key="sing.in"/></h1>

                <c:if test="${errorMessage=='true'}">
                    <div class="alert alert-danger fade show" role="alert">
                        <fmt:message bundle="${local}" key="error.login"/>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/onlineStore?command=checkLogin" method="post">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label"><fmt:message bundle="${local}"
                                                                                        key="user.email"/></label>
                        <div class="input-group w-100">
                            <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                                   aria-describedby="emailHelp" placeholder="example@example.com">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="passwordInput" class="form-label"><fmt:message bundle="${local}"
                                                                                   key="user.password"/></label>
                        <div class="input-group w-100">
                            <input name="password" type="password" class="form-control" id="passwordInput"
                                   placeholder="password">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message bundle="${local}" key="sing.in"/></button>
                </form>
                <hr class="dropdown-divider">
                <p class="text-muted"><fmt:message bundle="${local}" key="que.account"/> <a
                        href="${pageContext.request.contextPath}/onlineStore?command=signup"><fmt:message bundle="${local}" key="log.up"/></a></p>
                <a href="${pageContext.request.contextPath}/onlineStore?command=home"><fmt:message bundle="${local}" key="home"/></a>
            </c:if>
        </div>
        <div class="col"></div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>