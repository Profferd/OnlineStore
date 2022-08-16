<%--
  Created by IntelliJ IDEA.
  User: dgrus
  Date: 6/22/2022
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Add order</title>
    <jsp:include page="base/headerLink.jsp"/>
</head>
<body class="d-flex flex-column h-100">
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources.textResources" var="local"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col"></div>
        <div class="col-6 my-sm-3">
            <c:if test="${sessionScope.user == null}">
                <div class="alert alert-danger fade show " role="alert">
                    <fmt:message bundle="${local}" key="bucket.noAccess"/>
                </div>
            </c:if>
            <c:if test="${sessionScope.user != null}">
            <c:if test="${errorMessage=='true'}">
                <div class="alert alert-danger fade show" role="alert">
                    <fmt:message bundle="${local}" key="local.Error"/>
                </div>
            </c:if>

            <h4 class="mt-5 fw-bold"><fmt:message bundle="${local}" key="order.detail"/></h4>

            <form action="${pageContext.request.contextPath}/onlineStore?command=confirmOrder" method="post">

                <div class="mb-3">
                    <label for="address" class="form-label"><fmt:message bundle="${local}"
                                                                         key="user.address"/></label>
                    <input type="text" class="form-control" name="address" id="address"
                           placeholder="City, Street **" required>
                </div>

                <div class="mb-3">
                    <label for="delivery-date" class="form-label"><fmt:message bundle="${local}"
                                                                               key="prod.delDay"/></label>
                    <div class="input-group w-100">
                        <input type="date" class="form-control" id="delivery-date" name="delivery-date" placeholder="date" required></div>
                </div>

                <h3 class="text-primary"><fmt:message bundle="${local}" key="bucket.pay"/>:
                    <c:out value="${totalCost}"/> <fmt:message bundle="${local}"
                                                               key="price.val"/></h3>
                <div class="col-sm"></div>

                <button class="btn btn-primary" type="submit" name="command" value="confirmOrder"><fmt:message bundle="${local}"
                                                                                                               key="confirm"/></button>

            </form>
            <hr class="dropdown-divider">
            <a href="${pageContext.request.contextPath}/onlineStore?command=home"><fmt:message bundle="${local}" key="home"/></a>
        </div>
        </c:if>
        <div class="col-sm"></div>
    </div>
</div>
</body>
</html>
