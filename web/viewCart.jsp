<%-- 
    Document   : viewCart
    Created on : Feb 28, 2025, 10:29:29 AM
    Author     : TXtua
--%>

<%@page import="java.util.Map"%>
<%@page import="ducpt.entity.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <h1>Your Cart Items: </h1>
        <%
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    if (cart.getItems() != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <form action="MainController">
                    <%
                        Map<String, Integer> items = cart.getItems();
                        int count = 0;
                        for (Map.Entry item : items.entrySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= item.getKey()%>
                        </td>
                        <td>
                            <%= item.getValue()%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= item.getKey()%>" />
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3"><a href="bookStore.html">Add more item to cart</a></td>
                        <td>
                            <input type="submit" value="remove_item_from_cart" name="btAction" />
                        </td>
                    </tr>
            </form>
        </tbody>
    </table>


    <%
    } else {
    %>
    <h2>Cart empty!</h2>
    <%
                }
            }
        }
    %>
    <a href="bookStore.html">Add more item to cart</a>
</body>
</html>
