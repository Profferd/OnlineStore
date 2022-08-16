<%--
  Created by IntelliJ IDEA.
  User: dgrus
  Date: 6/22/2022
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="base/header.jsp"/>
    <title>Promotions</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources.textResources" var="local"/>
<div class="container">
    <c:forEach var="promotion" items="${promotion}">
        <div class="my-lg-4">
            <div class="col-6"></div>
            <div class="card text-center">
                <div class="card-header">
                    <h3><c:out value="${promotion.name}"/>
                        <small class="text-muted"> #<c:out value="${promotion.id}"/></small>
                    </h3>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-8">
                            <img src="images/<c:out value="${promotion.photo}"/>" class="img-fluid" width="600"
                                 height="800">
                        </div>
                        <div class="col-md-4">
                            <h3><fmt:message bundle="${local}" key="store.discounts"/>: <c:out
                                    value="${promotion.discount}"/>%</h3>
                            <p><fmt:message bundle="${local}" key="product.descr"/>: <c:out
                                    value="${promotion.description}"/></p>
                        </div>
                    </div>

                </div>
                <div class="card-footer text-muted">
                    <fmt:message bundle="${local}" key="discount.from"/> <c:out value="${promotion.startDate}"/>
                    <fmt:message bundle="${local}" key="discount.to"/> <c:out value="${promotion.endDate}"/>
                </div>
                <a href="${pageContext.request.contextPath}/onlineStore?command=promotions&page=1"><fmt:message bundle="${local}" key="page.first"/></a>
                <c:if test="${page != 1}">
                    <td><a href="onlineStore?command=promotions&page=${page - 1}"><--</a></td>
                </c:if>
                <b>${requestScope.page}</b>
                <c:if test="${page lt nuPages}">
                    <td><a href="onlineStore?command=promotions&page=${page + 1}">--></a></td>
                </c:if>
                <a href="${pageContext.request.contextPath}/onlineStore?command=promotions&page=${requestScope.nuPages}"><fmt:message bundle="${local}" key="page.last"/></a>
                <br>
            </div>
        </div>
    </c:forEach>
    <p class="text-secondary">* <fmt:message bundle="${local}" key="promotion.note"/></p>
</div>
</body>
</html>
