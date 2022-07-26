<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="/assets/head.jsp"%>
</head>
<body>
<h1>
    LIST PRODUCT
</h1>

<a href="/products?action=create">
    <button type="button"> +Add new product</button>
</a>
<div class="">

    <form method="get" enctype="multipart/form-data">
        <h2>Tìm kiếm sản phẩm</h2>
        <div>
            Từ khoá : <input type="text"  name="key">
        </div>
        <button class="btn" type="submit" name="search">
            <i class="fe-search">Tìm kiếm nè
            </i>
        </button>
    </form>

    <div>
        <table class="table table-hover"  border="2px">
            <thead style="font-size: 23px; color: #555d2f;">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>PRICE</th>
                <th>QUANTITY</th>
                <th>COLOR</th>
                <th>DESCRIBE</th>
                <th>CATEGORY</th>
                <th>ACTION</th>
            </tr>
            </thead>
            <tbody style="font-style: oblique;">
            <c:forEach items="${requestScope['productList']}" var="item">
                <tr>
                    <td>${item.getId()}</td>
                    <td>${item.getName()}</td>
                    <td>${item.getPrice()}</td>
                    <td>${item.getQuantity()}</td>
                    <td>${item.getColor()}</td>
                    <td>${item.getDescribe()}</td>
                    <td>
                        <c:forEach items="${applicationScope.listCategory }" var = "type">
                            <c:if test="${type.getId() == item.getIdCategory()}">
                                <c:out value="${type.getName()}"/>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="/products?action=edit&id=${item.getId()}">
                            <button type="button" class="btn btn-outline-primary"> <i class="fa-solid fa-pen-to-square"></i></button>
                        </a>
                        <a href="/products?action=delete&id=${item.getId()}">
                            <button type="button" class="btn btn-outline-primary"><i class="fa-solid fa-trash-can"></i></button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
