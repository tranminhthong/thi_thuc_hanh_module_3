<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <a href="/products?ac=add_product" class="btn btn-warning">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                Add product
            </a>
        </div>
        <div class="col-md-3">
            <form action="/products?ac=search_product" method="post">
                <input type="text" name="search" placeholder="Searching for">
                <button type="submit" class="btn btn-primary">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                </button>
            </form>
        </div>
    </div>
    <div id="list" class="row" style="background-color: #bcb6b6;margin-top: 20px;border: 1px solid black;border-bottom: none">
        <h4 style="margin-left: 5px"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> Product List</h4>
    </div>
    <div class="row" style="border: 1px solid  black">
        <div style="margin: 10px">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Color</th>
                    <th scope="col">Category</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <c:set var="x" value="1"/>
                <tbody>
                <c:forEach items="${requestScope['products']}" var="p">
                    <tr>
                        <th scope="row"><c:out value="${x}"/></th>
                        <td>${p.getName()}</td>
                        <td>${p.getPrice()}</td>
                        <td>${p.getQuantity()}</td>
                        <td>${p.getColor()}</td>
                        <td>${p.getCategory().getName()}</td>
                        <td>
                            <a href="/products?ac=edit_product&id=${p.getId()}">
                                <button type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                </button>
                            </a>
                            <a href="/products?ac=delete_product&id=${p.getId()}">
                                <button type="button" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                            </a>
                        </td>
                    </tr>
                    <c:set var="x" value="${x+1}"/>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
    <div class="row">
        <div style="margin-top: 25px;" class="col-md-2">
            <p>Show 0 to 0 of 0 entries</p>
        </div>
        <div class="col-md-3"></div>
        <div class="col-md-7">
            <div style="margin-top: 25px;" class="col-md-5">
                Show
                <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                    <option selected>Choose...</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
                entries
            </div>
            <div class="col-md-7">
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">Previous</span>
                        </a>
                    </li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">Next</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
