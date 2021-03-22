<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>

<div class="container">
    <div class="col-md-2"></div>
    <div style="background-color: #5cd2b9;margin-top: 150px" class="col-md-8">
        <form style="margin: 20px" method="post">
            <div class="row"><h2> Add new product </h2></div>
            <div class="row">
                <div class="form-group">
                    <label for="inputName">Product Name</label>
                    <input name="name_product" type="text" class="form-control" id="inputName"
                           placeholder="Enter name product">
                </div>
                <div class="form-group">
                    <label for="inputPrice">Price</label>
                    <input name="price_product" type="number" class="form-control" id="inputPrice"
                           placeholder="Enter price">
                </div>
                <div class="form-group">
                    <label>Quantity</label>
                    <input name="quantity" type="number" class="form-control" placeholder="Enter quantity">
                </div>
                <div class="form-group">
                    <label>Color</label>
                    <input name="color" type="text" class="form-control" placeholder="Enter color">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input name="des" type="text" class="form-control" placeholder="Enter description">
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <div class="checkbox">
                        <select name="cate_id">
                            <c:forEach items="${requestScope['categories']}" var="c">
                                <option value="${c.getId()}">${c.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="/products" class="btn btn-success">Back to List</a>
            </div>
        </form>
    </div>
    <div class="col-md-2"></div>
</div>
</body>
</html>
