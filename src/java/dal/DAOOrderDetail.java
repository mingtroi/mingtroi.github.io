/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.CartItem;
import model.OrderDetail;
import model.Orders;

/**
 *
 * @author nhat minh
 */
public class DAOOrderDetail extends DBContext{
    
    public List<OrderDetail> getAllOrderDetailById(int id) {
        List<OrderDetail> OrderDetails = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetail Where order_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail order = new OrderDetail();
                order.setId(rs.getInt(1));
                order.setOrderid(rs.getInt(2));
                order.setProductname(rs.getString(3));
                order.setProductimage(rs.getString(4));
                order.setProductprice(rs.getFloat(5));
                order.setQuantity(rs.getInt(6));

                OrderDetails.add(order);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return OrderDetails;
    }
    public void insertOrderDetail(int orders,Cart cart)
    {
        String sql = "INSERT INTO [dbo].[OrderDetail]\n" +
"           ([order_id]\n" +
"           ,[productName]\n" +
"           ,[productImage]\n" +
"           ,[productPrice]\n" +
"           ,[quantity])\n" +
"     VALUES\n" +
"			(?,?,?,?,?)";
        try
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orders);
            for (CartItem item : cart.getItem()) {
                stm.setString(2, item.getProduct().getName());
                stm.setString(3, item.getProduct().getImage());
                stm.setFloat(4, item.getProduct().getPrice());
                stm.setInt(5, item.getAmount());
                stm.executeUpdate();
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public List<OrderDetail> getAllOrderDetail() {
        List<OrderDetail> OrderDetails = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetail";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail order = new OrderDetail();
                order.setId(rs.getInt(1));
                order.setOrderid(rs.getInt(2));
                order.setProductname(rs.getString(3));
                order.setProductimage(rs.getString(4));
                order.setProductprice(rs.getFloat(5));
                order.setQuantity(rs.getInt(6));

                OrderDetails.add(order);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return OrderDetails;
    }
    public static void main(String[] args) {
        DAOOrderDetail dao = new DAOOrderDetail();
        List<OrderDetail> Order = dao.getAllOrderDetail();
        for(OrderDetail order : Order)
        {
            System.out.println(order);
        }
    }
  
//    public static void main(String[] args) {
//        DAOOrderDetail dao = new DAOOrderDetail();
//        List<OrderDetail> order = dao.getAllOrderDetailById(1);
//        System.out.println(order);
//    }
}
