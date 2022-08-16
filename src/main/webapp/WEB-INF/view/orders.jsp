<%--
  Created by IntelliJ IDEA.
  User: dgrus
  Date: 6/22/2022
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="base/header.jsp"/>
<head>
    <title>Order</title>
    <jsp:include page="base/headerLink.jsp"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources.textResources" var="local"/>
<div class="p-3">
    <div class="row justify-content-center">
        <c:if test="${sessionScope.user == null}">
            <div class="alert alert-danger fade show " role="alert">
                <fmt:message bundle="${local}" key="bucket.noAccess"/>
            </div>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col"><fmt:message bundle="${local}" key="order.num"/></th>
                    <th scope="col"><fmt:message bundle="${local}" key="bucket.title"/></th>
                    <th scope="col"><fmt:message bundle="${local}" key="bucket.number"/></th>
                    <th scope="col"><fmt:message bundle="${local}" key="user.address"/></th>
                    <th scope="col"><fmt:message bundle="${local}" key="order.date"/></th>
                    <th scope="col"><fmt:message bundle="${local}" key="prod.delDay"/></th>
                    <th scope="col"><fmt:message bundle="${local}" key="order.status"/></th>
                </tr>
                </thead>
                <c:forEach var="userOrder" items="${userOrder}">
                    <tr>
                        <th scope="row">#<c:out value="${userOrder.id}"/></th>
                        <td class="text-primary">
                            <c:forEach var="order" items="${order}">
                                <c:forEach var="product" items="${product}">
                                    <c:if test="${product.id == order.productId && userOrder.id == order.orderId}"> <p><c:out value="${product.name}"/></p>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </td>
                        <td class="text-primary">
                            <c:forEach var="order" items="${order}">
                                <c:if test="${userOrder.id == order.orderId}">
                                    <p><c:out value="${order.number}"/></p>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><c:out value="${userOrder.address}"/></td>
                        <td><c:out value="${userOrder.orderDate}"/></td>
                        <td><c:out value="${userOrder.deliveryDate}"/></td>
                        <c:if test="${userOrder.status == 'taken'}">
                            <td class="text-success"><c:out value="${userOrder.status}"/></td>
                        </c:if>
                        <c:if test="${userOrder.status == 'pending'}">
                            <td class="text-active"><c:out value="${userOrder.status}"/></td>
                        </c:if>
                        <c:if test="${userOrder.status == 'canceled'}">
                            <td class="text-danger"><c:out value="${userOrder.status}"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
