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
import model.Orders;

/**
 *
 * @author nhat minh
 */
public class DAOOrder extends DBContext{
    public List<Orders> getAllOrder() {
        List<Orders> Orders = new ArrayList<>();
        try {
            String sql = "select * from [Orders]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt(1));
                order.setAccountid(rs.getInt(2));
                order.setTotalprice(rs.getFloat(3));
                order.setNote(rs.getString(4));
                order.setCreatedate(rs.getString(5));
                order.setShippingid(rs.getInt(6));
                
                Orders.add(order);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Orders;
    }
    public boolean insertOrder(Orders order) {
        String sql = "INSERT INTO [dbo].[Orders] ([account_id], [totalPrice], [note], [shipping_id]) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, order.getAccountid());
            stm.setFloat(2, order.getTotalprice());
            stm.setString(3, order.getNote());
            stm.setInt(4, order.getShippingid());
            stm.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
//    public static void main(String[] args) {
//        DAOOrder dao = new DAOOrder();
//        List<Orders> Order = dao.getAllOrder();
//        for(Orders order : Order)
//        {
//            System.out.println(order);
//        }
//    }
     public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();
        
        // Insert a new order (example data)
        Orders orders = new Orders(46, 100.0f, "Example note", 46);
        boolean check = dao.insertOrder(orders);
        if (check) {
            System.out.println("Insert success");
        } else {
            System.out.println("Insert failed");
        }
    }
}
