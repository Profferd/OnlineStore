<%--
  Created by IntelliJ IDEA.
  User: dgrus
  Date: 6/23/2022
  Time: 12:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <jsp:include page="base/headerLink.jsp"/>
    <title>Edit product</title>
</head>
<body class="d-flex flex-column h-100">
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="textResources.textResources" var="local"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-6 my-sm-3">
            <c:if test="${sessionScope.role.name != 'admin'}">
                <div class="alert alert-danger fade show " role="alert">
                    <fmt:message bundle="${local}" key="bucket.noAccess"/>
                </div>
            </c:if>
            <c:if test="${sessionScope.role.name == 'admin'}">

                <c:if test="${param.message == 'error'}">
                    <div class="alert alert-danger fade show " role="alert">
                        <fmt:message bundle="${local}" key="admin.errorAddingProd"/>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/onlineStore?command=confirmEditProduct"
                      method="post">
                    <h4><fmt:message bundle="${local}" key="admin.prodDet"/></h4>

                    <div class="mb-3">
                        <label for="title" class="form-label"><fmt:message bundle="${local}"
                                                                           key="admin.prodName"/></label>
                        <input type="text" class="form-control" name="product-name" id="title"
                               placeholder="<fmt:message bundle="${local}" key="admin.prodName"/>" required
                               value="${product.name}">
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm mb-3">
                            <label for="photo" class="form-label"><fmt:message bundle="${local}"
                                                                               key="admin.prodPhoto"/></label>
                            <input type="text" id="photo" name="photo" class="form-control"
                                   placeholder="<fmt:message bundle="${local}" key="admin.prodPhoto"/>" required
                                   value="${product.photo}">
                        </div>

                        <div class="col-sm">
                            <label for="price" class="form-label"><fmt:message bundle="${local}"
                                                                               key="product.price"/></label>
                            <input type="text" id="price" name="price" class="form-control"
                                   placeholder="100.15" pattern="^\d+\.\d{0,2}$" required
                                   value="${product.price}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm mb-3">
                            <label for="category" class="form-label"><fmt:message bundle="${local}" key="product.category"/></label>
                            <input type="text" name="category" id="category" class="form-control"
                                   placeholder="<fmt:message bundle="${local}" key="product.category"/>" required
                                   value="${category.category}">
                        </div>

                        <div class="col-sm mb-3">
                            <label for="promotion" class="form-label"><fmt:message bundle="${local}" key="promotion.id"/></label>
                            <input type="text" name="promotion" id="promotion" class="form-control" pattern="(?<![-.])\b[0-9]+\b(?!\.[0-9])"
                                   placeholder="<fmt:message bundle="${local}" key="promotion.id"/>" required
                                   value="${product.promotionId}">
                        </div>

                        <div class="col-sm">
                            <fieldset class="form-group">
                                <div class="form-check">
                                    <input type="checkbox" name="availability" class="form-check-input" id="exampleCheck1"
                                    <c:if test="${product.status == true}">
                                           checked
                                    </c:if>
                                    <label class="form-check-label" for="exampleCheck1"><fmt:message bundle="${local}" key="product.available"/></label>
                                </div>
                            </fieldset>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label"><fmt:message bundle="${local}"
                                                                                 key="product.descr"/></label>
                        <textarea class="form-control" name="description" id="description"
                                  placeholder="<fmt:message bundle="${local}" key="product.descr"/>" required><c:out value="${product.description}"/>
                        </textarea>
                    </div>

                    <input type="hidden" name="productId" value="${param.productId}">

                    <button class="btn btn-primary" type="submit"><fmt:message bundle="${local}"
                                                                               key="confirm"/></button>
                </form>
                <hr class="dropdown-divider">
                <a href="${pageContext.request.contextPath}/onlineStore?command=home"><fmt:message bundle="${local}" key="home"/></a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
