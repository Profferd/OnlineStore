<%--
  Created by IntelliJ IDEA.
  User: dgrus
  Date: 6/21/2022
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Log Up</title>
    <jsp:include page="base/headerLink.jsp"/>
</head>
<body class="d-flex flex-column h-100">
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources.textResources" var="local"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col"></div>
        <div class="col-6 my-sm-3">
            <c:if test="${sessionScope.user != null}">
                <div class="alert alert-danger fade show" role="alert">
                    <fmt:message bundle="${local}" key="Logged.In"/>
                </div>
            </c:if>
            <c:if test="${sessionScope.user == null}">
            <h1 class="mt-5 fw-bold"><fmt:message bundle="${local}" key="log.up"/></h1>

            <c:if test="${message == 'error'}">
                <div class="alert alert-danger fade show " role="alert">
                    <fmt:message bundle="${local}" key="registration.failed"/>
                </div>
            </c:if>
            <c:if test="${message == 'ok'}">
                <div class="alert alert-success fade show " role="alert">
                    <fmt:message bundle="${local}" key="registration.success"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/onlineStore?command=registration" method="post">
                <div class="row mb-3">
                    <div class="col-sm mb-3">
                        <label for="name" class="form-label"><fmt:message bundle="${local}" key="user.name"/></label>
                        <input type="text" id="name" name="name" class="form-control"
                               placeholder="<fmt:message bundle="${local}" key="user.name"/>" required maxlength="45"
                               pattern="\b[A-ZА-Я].*?\b">
                    </div>
                    <div class="col-sm">
                        <label for="surname" class="form-label"><fmt:message bundle="${local}"
                                                                             key="user.surname"/></label>
                        <input type="text" id="surname" name="surname" class="form-control"
                               placeholder="<fmt:message bundle="${local}" key="user.surname"/>" required
                               maxlength="45"
                               pattern="\b[A-ZА-Я].*?\b">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-sm">
                        <label for="phone" class="form-label"><fmt:message bundle="${local}"
                                                                           key="user.phone"/></label>
                        <input type="text" id="phone" name="phone" class="form-control"
                               placeholder="<fmt:message bundle="${local}" key="user.phone"/>" required
                               maxlength="45"
                               pattern="\+?[0-9\s\-\(\)]+">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label"><fmt:message bundle="${local}"
                                                                       key="user.email"/></label>
                    <input type="email" class="form-control" name="email" id="email"
                           placeholder="example@example.com" required>
                </div>
                <div class="mb-3">
                    <label for="password-first" class="form-label"><fmt:message bundle="${local}"
                                                                                key="user.password"/></label>
                    <input type="password" class="form-control" name="password-first" id="password-first"
                           placeholder="password" required
                           minlength="8">
                </div>
                <div class="mb-3">
                    <label for="password-second" class="form-label"><fmt:message bundle="${local}"
                                                                                 key="confirm.pass"/></label>
                    <input type="password" class="form-control" name="password-second" id="password-second"
                           placeholder="password" required
                           minlength="8">
                </div>

                <button class="btn btn-primary" type="submit"><fmt:message bundle="${local}"
                                                                           key="log.up"/></button>

            </form>
            <hr class="dropdown-divider">
            <p class="text-muted"><fmt:message bundle="${local}" key="login.haveAccount"/><a
                    href="${pageContext.request.contextPath}/onlineStore?command=login"> <fmt:message
                    bundle="${local}" key="log.in"/></a></p>
            <a href="${pageContext.request.contextPath}/onlineStore?command=home"><fmt:message bundle="${local}" key="home"/></a>
        </div>
        </c:if>
        <div class="col-sm"></div>
    </div>
</div>


</body>
</html>
