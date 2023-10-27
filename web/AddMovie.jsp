<%-- 
    Document   : AddToCart
    Created on : Oct 27, 2023, 1:20:04 AM
    Author     : ACER
--%>
<%@page import="java.util.Iterator"%>
<%@page import="model.ProductsTable"%>
<%@page import="model.Shoppingcart"%>
<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Products" %>
<%@page import="java.util.List"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Products</title>
    <script>
            window.addEventListener('DOMContentLoaded', function() {
            var checkboxes = document.querySelectorAll('input[type="checkbox"]');

            checkboxes.forEach(function(checkbox) {
                checkbox.addEventListener('change', function() {
                var input = document.querySelector("[name='quantity" + this.value + "']");
                input.readOnly = !this.checked;
                if (!this.checked) {
                    input.value = ""; 
                }
            });
        });
    });
    </script>
</head>
<jsp:useBean id="product" class="model.Products" scope="request"/>
<jsp:useBean id="shoppingcart" class="model.Shoppingcart" scope="request"/>
<%
    List<Products> movie = ProductsTable.findAllProducts();
    Iterator<Products> itr = movie.iterator();
%>
<body>
    <center>
        <h1>show Movie</h1>
        <form action="AddMovieController" method="post">
            <table border="1">
                <tr>
                    <th>Movie name</th>
                    <th>Rating</th>
                    <th>Year</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
                <%
                    while(itr.hasNext()) {
                        product = itr.next();
                %>
                <tr>
                    <td><input type="checkbox" name="selectedProducts" value="<%= product.getId()%>" onchange="toggleInput(this)"></td>
                    <td><%= product.getRating() %></td>
                    <td><%= product.getYearcreate() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><input type="number" name="quantity<%= product.getId() %>" value="" min="0" readonly/></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </form>
        <a href="index.html"><input type="submit" value="Back to homepage"></a>
        <a href="DVDCartList.jsp"><input type="submit" value="Add to Cart"></a>
    </center>
</body>
</html>
