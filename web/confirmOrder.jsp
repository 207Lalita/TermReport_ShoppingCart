<%-- 
    Document   : confirmOrder
    Created on : Oct 27, 2023, 10:42:30 PM
    Author     : ACER
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.Shoppingcart"%>
<%@page import="model.ShoppingcartTable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show Confirm Order</title>
</head>
<body>
    <h1>Your Order is confirmed!</h1>
    <h1><%=request.getAttribute("totalText")%> </h1>
</body>
</html>
