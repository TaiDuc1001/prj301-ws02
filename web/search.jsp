<%-- 
    Document   : search
    Created on : Mar 6, 2025, 6:38:03 PM
    Author     : LENOVO-DUCKY
--%>

<%@page import="java.util.List"%>
<%@page import="ducpt.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h2>Search page!</h2>
        <form action="MainController">
            <%
                String searchValue = request.getParameter("txtSearchValue"); 
            %>
            Search value: <input type="text" name="txtSearchValue" value="<%= searchValue != null ? searchValue : "" %>" />
            <br>
            <input type="submit" value="search" name="btAction" />
        </form>

        <%
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCHRESULT");
                if (result != null && !result.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Lastname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "MainController?btAction=Del&pk="
                                + dto.getUsername()
                                + "&lastSearchValue="
                                + searchValue;
                %>
                <tr>
                    <form action="MainController">
                        <td><%= ++count %></td>
                        <td>
                            <%= dto.getUsername() %>
                            <input type="hidden" name="txtUsername" value="<%= dto.getUsername() %>" />
                        </td>
                        <td>
                            <input type="text" name="txtPassword" value="<%= dto.getPassword() %>" />
                        </td>
                        <td>
                            <input type="text" name="txtLastname" value="<%= dto.getLastname() %>" />
                        </td>
                        <td>
                            <input type="checkbox" name="chkAdmin" value="ON" <%= dto.isRole() ? "checked" : "" %> />
                        </td>
                        <td>
                            <a href="<%= urlRewriting %>">Delete</a>
                        </td>
                        <td>
                            <input type="hidden" name="lastSearchValue" value="<%= searchValue %>" />
                            <input type="submit" value="Update" name="btAction" />
                        </td>
                    </form>
                </tr>
                <% } %>
            </tbody>
        </table>
        <%
                } else {
        %>
        <h2>No record is matched!</h2>
        <% 
                } 
            } 
        %>
    </body>
</html>

