<%--
  Created by IntelliJ IDEA.
  User: dgrus
  Date: 6/22/2022
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="base/header.jsp"/>
<head>
    <title>Profile</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources.textResources" var="local"/>

<div class="container">
    <c:if test="${sessionScope.user == null}">
        <div class="row justify-content-center">
            <div class="alert alert-danger fade show " role="alert">
                <fmt:message bundle="${local}" key="bucket.noAccess"/>
            </div>
        </div>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <div class="p-3 p-md-4 border rounded-3 my-sm-3">
            <div class="col"></div>
            <div class="col-6">
                <h2><fmt:message bundle="${local}" key="user.profile"/></h2>
            </div>
            <table class="table">
                <tr>
                    <td width="300px">
                        <h4 class="text-primary">
                            <fmt:message bundle="${local}" key="user.name"/>:
                        </h4>
                        <h4 class="text-primary">
                            <fmt:message bundle="${local}" key="user.surname"/>:
                        </h4>
                    </td>
                    <td><h4><c:out value="${userInfo.name}"/></h4>
                        <h4><c:out value="${userInfo.surname}"/></h4>
                    </td>
                </tr>
                <tr>

                    <td width="300px"><h4 class="text-primary"><fmt:message bundle="${local}"
                                                                            key="user.phone"/>:</h4></td>
                    <td><h4>+<c:out value="${userInfo.phone}"/></h4></td>
                </tr>
                <tr>
                    <td width="300px"><h4 class="text-primary"><fmt:message bundle="${local}"
                                                                            key="user.email"/>:</h4></td>
                    <td><h4>${sessionScope.user.email}</h4></td>
                </tr>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>
