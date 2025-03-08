<%-- 
    Document   : createnewAccount
    Created on : Mar 4, 2025, 10:46:38 AM
    Author     : TXtua
--%>

<%@page import="ducpt.registration.RegistrationInsertError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account Page</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="MainController">
            Username: <input type="text" name="txtUsername" value="" />
            <%
                RegistrationInsertError errors = (RegistrationInsertError) request.getAttribute("INSERTERRORS");
                if (errors != null && errors.getUsernameLengthErr() != null) {
            %>
                <font color="red"><%= errors.getUsernameLengthErr() %></font>
            <%
                }
            %>
            <br><br>

            Password: <input type="password" name="txtPassword" value="" />
            <%
                if (errors != null && errors.getPasswordLengthErr() != null) {
            %>
                <font color="red"><%= errors.getPasswordLengthErr() %></font>
            <%
                }
            %>
            <br><br>

            Confirm: <input type="password" name="txtConfirm" value="" />
            <%
                if (errors != null && errors.getConfirmNoMatch() != null) {
            %>
                <font color="red"><%= errors.getConfirmNoMatch() %></font>
            <%
                }
            %>
            <br><br>

            Firstname: <input type="text" name="txtFirstname" value="" />
            <%
                if (errors != null && errors.getFirstnameLengthErr() != null) {
            %>
                <font color="red"><%= errors.getFirstnameLengthErr() %></font>
            <%
                }
            %>
            <br><br>

            Lastname: <input type="text" name="txtLastname" value="" />
            <%
                if (errors != null && errors.getLastnameLengthErr() != null) {
            %>
                <font color="red"><%= errors.getLastnameLengthErr() %></font>
            <%
                }
            %>
            <br><br>

            <input type="submit" value="create_new_account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>

