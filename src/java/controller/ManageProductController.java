/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Product;

/**
 *
 * @author nhat minh
 */
@WebServlet(name="ManageProductController", urlPatterns={"/manage-product"})
public class ManageProductController extends HttpServlet {
   
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
            out.println("<title>Servlet ManageProductController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageProductController at " + request.getContextPath () + "</h1>");
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
        DAOProduct dao = new DAOProduct();
        List<Product> ProductList = dao.getAllProducts();
        int totalProduct = dao.getTotalProduct();
        request.setAttribute("ProductList", ProductList);
        request.setAttribute("totalProduct", totalProduct);
        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
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
        DAOProduct dao = new DAOProduct();
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        float price = Float.parseFloat(request.getParameter("price"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String cateidStr = request.getParameter("cateid");
        int cateid = 1;
        if (cateidStr != null && !cateidStr.isEmpty()) {
            try {
                cateid = Integer.parseInt(cateidStr);
            } catch (NumberFormatException e) {
                request.setAttribute("mess", "Invalid cateid value");
                request.getRequestDispatcher("AddNewProduct.jsp").forward(request, response);
                return;
            }
        }
        else
        {
            cateid = 1;
        }
        int sellid = Integer.parseInt(request.getParameter("sellid"));
        boolean insert = dao.insertProduct(name, image, price, title, description, cateid, sellid);
        if (insert) {
            response.sendRedirect("manage-product");
        } else {
            request.setAttribute("mess", "Failed to insert Category");
            request.getRequestDispatcher("AddNewProduct.jsp").forward(request, response);
        }
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
