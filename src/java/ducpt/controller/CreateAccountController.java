/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducpt.controller;

import ducpt.registration.RegistrationDAO;
import ducpt.registration.RegistrationInsertError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO-DUCKY
 */
@WebServlet(name = "CreateAccountController", urlPatterns = {"/CreateAccountController"})
public class CreateAccountController extends HttpServlet {
    private final String LOGINPAGE = "login.html";
    private final String CREATEACCOUNT = "createNewAccount.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = CREATEACCOUNT;
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String firstname = request.getParameter("txtFirstname");
            String lastname = request.getParameter("txtLastname");
            out.println("<h2>" + username + ", " + password + ", " + confirm + ", " + firstname + ", " + lastname + "</h2>");
            RegistrationDAO dao = new RegistrationDAO();
            boolean bErros = false;
            RegistrationInsertError errors = new RegistrationInsertError();
            if (username.trim().length() < 6 || username.trim().length() > 15) {
                bErros = true;
                errors.setUsernameLengthErr("* Username length must be (6 - 15) chars *");
            }
            out.println("<h2>" + username + ", " + password + ", " + confirm + ", " + firstname + ", " + lastname + "</h2>");
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                bErros = true;
                errors.setPasswordLengthErr("* Password length must be (6-20) chars *");
            }
            if (!confirm.trim().equals(password.trim())) {
                bErros = true;
                errors.setConfirmNoMatch("* Confirm password not match *");
            }
            if (lastname.trim().length() < 2 || lastname.trim().length() > 50) {
                bErros = true;
                errors.setLastnameLengthErr("* Lastname length must be (2-50) chars *");
            }
            if (firstname.trim().length() < 2 || firstname.trim().length() > 50) {
                bErros = true;
                errors.setFirstnameLengthErr("* Firstname length must be (2-50) chars *");
            }
            if (bErros) {
                request.setAttribute("INSERTERRORS", errors);
            } else {
                boolean result = dao.insertRecord(username, password, firstname, lastname, false);
                if (result) {
                    url = LOGINPAGE;
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
