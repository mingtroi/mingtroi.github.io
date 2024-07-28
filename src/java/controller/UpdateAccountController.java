/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOAccount;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author nhat minh
 */
@WebServlet(name="UpdateAccountController", urlPatterns={"/update-account"})
public class UpdateAccountController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateAccountController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAccountController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("uid");
        int uid = Integer.parseInt(id);
        DAOAccount dao = new DAOAccount();
        Account account = dao.getAccountByUID(uid);
        request.setAttribute("account", account);
        request.getRequestDispatcher("EditAccount.jsp").forward(request, response);

    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Account ac = new Account();
DAOAccount dao = new DAOAccount();
String id = request.getParameter("uid");
int uid = Integer.parseInt(id);

String user = request.getParameter("user");
String pass = request.getParameter("pass");
 Account currentAccount = dao.getAccountByUID(uid);
// Fix for issell
String is_sell = request.getParameter("issell");
int issell = currentAccount.getIssell(); // Default to current value
if (is_sell != null && !is_sell.isEmpty()) {
    try {
        issell = Integer.parseInt(is_sell);
    } catch (NumberFormatException e) {
        System.out.println("Invalid issell value: " + is_sell);
    }
}

// Process isadmin
String is_admin = request.getParameter("isadmin");
int isadmin = currentAccount.getIsadmin(); // Default to current value
if (is_admin != null && !is_admin.isEmpty()) {
    try {
        isadmin = Integer.parseInt(is_admin);
    } catch (NumberFormatException e) {
        System.out.println("Invalid isadmin value: " + is_admin);
    }
}

String a = request.getParameter("active");
boolean active = Boolean.parseBoolean(a);

dao.updateAccount(user, pass, issell, isadmin, active, uid);
response.sendRedirect("edit-account");


    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
