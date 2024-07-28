/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOOrder;
import dal.DAOOrderDetail;
import dal.DAOShipping;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.Cart;
import model.CartItem;
import model.Orders;
import model.Shipping;

/**
 *
 * @author nhat minh
 */
@WebServlet(name="CheckOutController", urlPatterns={"/check-out"})
public class CheckOutController extends HttpServlet {
   
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
            out.println("<title>Servlet CheckOutController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutController at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        Cart cart;
        if(session.getAttribute("cart") != null)
        {
            cart = (Cart) session.getAttribute("cart");
        }
        else
        {
            cart = new Cart(new ArrayList<CartItem>(), 0);
        }
        float totalMoney = 0;
        for(CartItem item : cart.getItem())
        {
            totalMoney += item.getProduct().getPrice() * item.getAmount();
        }
        cart.setTotalPrice(totalMoney);
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("CheckOut.jsp").forward(request, response);
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
        DAOShipping daoshipping = new DAOShipping();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        
        Account account = (Account)request.getSession().getAttribute("account");
        Shipping shipping = new Shipping(account.getUid(),name, phone, address);
        int shippingId = daoshipping.insertShipping(shipping);

        HttpSession session = request.getSession();
        Cart cart = new Cart(new ArrayList<CartItem>(),0);
        if(session.getAttribute("cart") != null)
        {
            cart = (Cart) session.getAttribute("cart");
        }
        else
        {
            cart = new Cart(new ArrayList<CartItem>(),0);
        }
        float totalMoney = cart.getTotalMoney();
        Orders orders = new Orders(account.getUid(), totalMoney, note, shippingId);
        boolean orderID = new DAOOrder().insertOrder(orders);
        if (orderID) {
            int orderId = orders.getId();  
            new DAOOrderDetail().insertOrderDetail(orderId, cart);
        }
        session.removeAttribute("cart");
        request.setAttribute("carts", cart);
        request.getRequestDispatcher("Thanks.jsp").forward(request, response);
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
