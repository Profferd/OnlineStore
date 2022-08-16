<%--
  Created by IntelliJ IDEA.
  User: dgrus
  Date: 6/23/2022
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="base/header.jsp"/>
<head>
    <title>View order</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources.textResources" var="local"/>

<c:if test="${sessionScope.role.name != 'admin'}">
  <div class="container">
    <div class="row justify-content-center">
      <div class="alert alert-danger fade show " role="alert">
        <fmt:message bundle="${local}" key="bucket.noAccess"/>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${sessionScope.role.name == 'admin'}">
  <form action=${pageContext.request.contextPath}/onlineStore method="post">
    <div class="p-3">
      <div class="row justify-content-center">
        <table class="table table-hover">
          <thead>
          <tr>
            <th scope="col"><fmt:message bundle="${local}" key="order.id"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="user.fullName"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="user.phone"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="bucket.title"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="bucket.number"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="user.address"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="order.date"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="prod.delDay"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="order.status"/></th>
            <th scope="col"><fmt:message bundle="${local}" key="bucket.action"/></th>
          </tr>
          </thead>
          <c:forEach var="userOrder" items="${userOrder}">
            <tr>
              <th width="50" scope="row">#<c:out value="${userOrder.id}"/></th>
              <td width="150" class="text-primary">
                <c:set scope="request" var="userInfoNumberFlag" value="0"/>
                <c:forEach var="order" items="${order}">
                  <c:forEach var="user" items="${user}">
                    <c:forEach var="userInfo" items="${userInfo}">
                      <c:if test="${user.id == order.userId && userOrder.id == order.orderId &&
                                        userInfo.id == user.userInfoId && userInfoNumberFlag == 0}">
                        <p><c:out value="${userInfo.surname}"/>
                          <c:out value="${userInfo.name}"/>
                        </p>
                        <c:set scope="request" var="userInfoNumberFlag" value="1"/>
                      </c:if>
                    </c:forEach>
                  </c:forEach>
                </c:forEach>
              </td>
              <td class="text-primary">
                <c:set scope="request" var="userInfoNumberFlag" value="0"/>
                <c:forEach var="order" items="${order}">
                  <c:forEach var="user" items="${user}">
                    <c:forEach var="userInfo" items="${userInfo}">
                      <c:if test="${user.id == order.userId && userOrder.id == order.orderId &&
                                        userInfo.id == user.userInfoId && userInfoNumberFlag == 0}">
                        <p>+<c:out value="${userInfo.phone}"/></p>
                        <c:set scope="request" var="userInfoNumberFlag" value="1"/>
                      </c:if>
                    </c:forEach>
                  </c:forEach>
                </c:forEach>
              </td>
              <td>
                <c:forEach var="order" items="${order}">
                  <c:forEach var="product" items="${product}">
                    <c:if test="${product.id == order.productId && userOrder.id == order.orderId}">
                      <p><c:out value="${product.name}"/></p>
                    </c:if>
                  </c:forEach>
                </c:forEach>
              </td>
              <td>
                <c:forEach var="order" items="${order}">
                  <c:if test="${userOrder.id == order.orderId}">
                    <p><c:out value="${order.number}"/></p>
                  </c:if>
                </c:forEach>
              </td>
              <td><c:out value="${userOrder.address}"/></td>
              <td><c:out value="${userOrder.orderDate}"/></td>
              <td><c:out value="${userOrder.deliveryDate}"/></td>
              <td class="text-active"><c:out value="${userOrder.status}"/></td>
              <td>
                <button onclick="location.href = '${pageContext.request.contextPath}/onlineStore?command=complUserOrder&orderId=${userOrder.id}'"
                        type="button" class="btn btn-outline-success">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                       class="bi bi-check2" viewBox="0 0 16 16">
                    <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
                  </svg>
                </button>
              </td>
              <td>
                <button onclick="location.href = '${pageContext.request.contextPath}/onlineStore?command=deleteUserOrder&orderId=${userOrder.id}'"
                        class="btn btn-outline-danger" type="button">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                       fill="currentColor"
                       class="bi bi-x-circle" viewBox="0 0 16 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"></path>
                  </svg>
                </button>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </form>
</c:if>
</body>
</html>
