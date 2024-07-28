/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author nhat minh
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Shipping;
public class DAOShipping extends DBContext{
    public int insertShipping(Shipping shipping) {
        String sql = "INSERT INTO [dbo].[Shipping]\n" +
"           ([name]\n" +
"           ,[phone]\n" +
"           ,[address])\n" +
"     VALUES\n" +
"           (?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, shipping.getName());
            stm.setString(2, shipping.getPhone());
            stm.setString(3, shipping.getAddress());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1; 
    }
//    
//    public static void main(String[] args) {
//        DAOShipping dao = new DAOShipping();
//        boolean check = dao.insertShipping("m", "123", "madf");
//        if(check)
//        {
//            System.out.println("insert success");
//        }
//        else
//        {
//            System.out.println("Insert failed");
//        }
//    }
//        String sql = "INSERT INTO [dbo].[Shipping] ([name], [phone], [address]) OUTPUT Inserted.id VALUES (?, ?, ?)";}
}