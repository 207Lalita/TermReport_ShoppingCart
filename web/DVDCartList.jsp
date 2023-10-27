<%@page import="java.util.Iterator"%>
<%@page import="model.ShoppingcartPK"%>
<%@page import="model.Shoppingcart"%>
<%@page import="model.ShoppingcartTable"%>
<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="model.Products" %>
<%@page import="java.util.List"%>

<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
    </head>
    <jsp:useBean id="product" class="model.Products" scope="request"/>
    <jsp:useBean id="shoppingcart" class="model.Shoppingcart" scope="request"/>
<% 
    ShoppingcartPK cartPK = (ShoppingcartPK) request.getSession().getAttribute("cartPK");
    List<Shoppingcart> ListMovie = ShoppingcartTable.findListShoppingcartById(cartPK);
    //Collections.sort(products, (p1, p2) -> Integer.compare(p1.getId(), p2.getId()));
    Iterator<Shoppingcart> itr = ListMovie.iterator();
%>
<body>
    <form name="AddToCart" action="ConfirmOrderController" method="post">
        <center>
            <h1>Shopping Cart</h1>
            <table border="1">
            <tr>
                <th></th>
                <th>Movie name</th>
                <th>Rating</th>
                <th>Year</th>
                <th>Price/Unit</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <%
                int total = 0;
                while(itr.hasNext()) {
                    shoppingcart = itr.next();
                    total = shoppingcart.getQuantity()*shoppingcart.getProducts().getPrice();
            %>
            <tr>
                <td><%= shoppingcart.getProducts().getMovie() %></td>
                <td><%= shoppingcart.getProducts().getRating() %></td>
                <td><%= shoppingcart.getProducts().getYearcreate() %></td>
                <td><%= shoppingcart.getProducts().getPrice() %></td>
                <td><%= shoppingcart.getQuantity()%>
                <td><%= shoppingcart.getQuantity()*shoppingcart.getProducts().getPrice()%>
             
            </tr>
        <%
            }
        %>
        <tr>
            
        </tr>
    </table>
    </form>
    <a href="index.html"><input type="submit" value="Back to homepage"></a>
    <a href="ShoppingCart.jsp"><input type="submit" value="Add to Cart"></a>
</body>
</center>
</html>
