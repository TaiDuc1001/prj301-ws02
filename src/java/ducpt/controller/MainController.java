/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO-DUCKY
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    private final String INVALIDPAGE = "invalid.html";
    private final String LOGINCONTROLLER = "LoginController";
    private final String SEARCHCONTROLLER = "SearchController";
    private final String UPDATECONTROLLER = "UpdateController";
    private final String DELETECONTROLLER = "DeleteController";
    private final String ADDTOCARTCONTROLLER = "AddToCartController";
    private final String VIEWCARTPAGE = "viewCart.jsp";
    private final String REMOVEITEMCONTROLLER = "RemoveItemController";
    private final String CREATEACCOUNTCONTROLLER = "CreateAccountController";
    
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
            String action = request.getParameter("btAction");
            System.out.println("Action: " + action);
            String url = INVALIDPAGE;
            if (action.equals("login")){
                url = LOGINCONTROLLER;
            } else if (action.equalsIgnoreCase("search")){
                url = SEARCHCONTROLLER;
            } else if (action.equalsIgnoreCase("update")) {
                url = UPDATECONTROLLER;
            } else if (action.equalsIgnoreCase("del")) {
                url = DELETECONTROLLER;
            } else if (action.equalsIgnoreCase("add_book_to_cart")){
                url = ADDTOCARTCONTROLLER;
            } else if (action.equalsIgnoreCase("view_cart")){
                url = VIEWCARTPAGE;
            } else if (action.equalsIgnoreCase("remove_item_from_cart")){
                url = REMOVEITEMCONTROLLER;
            } else if (action.equalsIgnoreCase("create_new_account")){
                url = CREATEACCOUNTCONTROLLER;
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

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
