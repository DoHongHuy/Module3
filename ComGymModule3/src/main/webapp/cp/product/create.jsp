<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/26/2022
  Time: 8:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add new product</h1>
<div>
    <form method="post">
        <div>
            <label for="name" >Nhập tên sản phẩm</label> <br>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div>
            <label for="price" >Nhập giá sản phẩm</label> <br>
            <input type="number" required class="form-control" id="price" name="price">
        </div>
        <div>
            <label for="quantity" >Nhập số lượng</label> <br>
            <input type="number" required class="form-control" id="quantity" name="quantity">
        </div>
        <div>
            <label for="color" >Nhập màu sản phẩm</label> <br>
            <input type="text" required class="form-control" id="color" name="color">
        </div>
        <div>
            <label for="describe" >Mô tả sản phẩm</label> <br>
            <input type="text" required class="form-control" id="describe" name="describe">
        </div>
        <div>
            <label >Danh mục</label> <br>
            <select class="control-label" name="idCategory" id="idCategory" style="background-color: #110e0e; color: #fcf8e3;">
                <c:forEach items="${applicationScope.listCategory}"
                           var="type">
                    <option value="${type.getId() }" class="form-control" >${type.getName() }</option>
                </c:forEach>
            </select>
            </select>
        </div>
        <div>
            <c:forEach items="${requestScope['errors']}" var="item">
                <li>${item}</li>
            </c:forEach>
        </div>
        <div>
            <button class="w-100 btn btn-lg btn-primary" type="submit"  style="background-color: #9d6262;">Create</button>
        </div>
        <div>
            <a href="/products">
                <button type="button" >Back</button>
            </a>
        </div>
    </form>
</div>
</div>
</body>
</html>
