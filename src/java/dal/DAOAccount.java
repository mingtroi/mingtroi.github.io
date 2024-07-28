/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nhat minh
 */
public class DAOAccount extends DBContext{
    public List<Account> getAllAccount()
    {
        String sql = "select * from Account";
        try 
        {
            List<Account> list = new ArrayList<>();
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Account account = new Account();
                account.setUid(rs.getInt(1));
                account.setUser(rs.getString(2));
                account.setPass(rs.getString(3));
                account.setIssell(rs.getInt(4));
                account.setIsadmin(rs.getInt(5));
                account.setActive(rs.getBoolean(6));
                list.add(account);
            }
            return list;
        }
        catch(Exception ex)
        {
            Logger.getAnonymousLogger(DAOAccount.class.getName()).log(Level.SEVERE, null,ex);
            return null;
        }
    }
    
    public Account getAccountByUID(int id)
    {
        String sql = "select * from account where uid = ?";
        try 
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
               Account account = new Account();
               account.setUid(rs.getInt(1));
               account.setUser(rs.getString(2));
               account.setPass(rs.getString(3));
               account.setIssell(rs.getInt(4));
               account.setIsadmin(rs.getInt(5));
               account.setActive(rs.getBoolean(6)); 
               return account;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Account Login(String user, String pass)
    {
        String sql = "SELECT * FROM Account "
                + "WHERE [user] COLLATE Latin1_General_BIN = ? "
                + "AND pass COLLATE Latin1_General_BIN = ?";
        try
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2,pass);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                return new Account(rs.getInt(1),rs.getString(2), rs.getString(3),
                                   rs.getInt(4),rs.getInt(5),rs.getBoolean(6));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         return null;
    }
    
    public Account checkLogin(String user)
    {
        String sql = "SELECT * FROM Account "
                + "WHERE [user] COLLATE Latin1_General_BIN = ? ";
        try
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                return new Account(rs.getInt(1),rs.getString(2), rs.getString(3),
                                   rs.getInt(4),rs.getInt(5),rs.getBoolean(6));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         return null;
    }

        public boolean insertAccount(String user, String pass)
    {
        String sql = "INSERT INTO [dbo].[Account]\n" +
"           ([user]\n" +
"           ,[pass]\n" +
"           ,[isSell]\n" +
"           ,[isAdmin]\n" +
"           ,[active])\n" +
"     VALUES\n" +
"           (?,?,0,0,'true')";
        try
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            stm.executeUpdate();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
        public boolean deleteAccount(int id)
        {
            String sql = "delete from Account where uID = ?";
            try
            {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, id);
                stm.executeUpdate();
                return true;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                return false;
            }
        }
        
        public boolean updateAccount(String user, String pass, int issell,
                                     int isadmin, boolean active, int uid)
        {
            String sql = "UPDATE [dbo].[Account]\n" +
                        "   SET [user] = ?\n" +
                        "      ,[pass] = ?\n" +
                        "      ,[isSell] = ?\n" +
                        "      ,[isAdmin] = ?\n" +
                        "      ,[active] = ?\n" +
                        " WHERE [uID]  = ?";
            try
            {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, user);
                stm.setString(2, pass);
                stm.setInt(3, issell);
                stm.setInt(4, isadmin);
                stm.setBoolean(5, active);
                stm.setInt(6, uid);
                stm.executeUpdate();
                return true;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                return false;
            }
        }
  
//    public static void main(String[] args) {
//        DAOAccount dao = new DAOAccount();
//        boolean account = dao.updateAccount("a", "a",
//                                            1,1,true,75);
//        if(account)
//        {
//            System.out.println("success");            
//        }
//        else
//        {
//            System.out.println("failed");        
//        }
//    } 
//    public static void main(String[] args) {
//            DAOAccount dao = new DAOAccount();
//            boolean account = dao.deleteAccount(74);
//            if(account)
//            {
//                System.out.println("success");            
//            }
//            else
//            {
//                System.out.println("failed");        
//            }
//    } 
//    public static void main(String[] args) {
//        DAOAccount dao = new DAOAccount();
//        boolean account = dao.insertAccount("qqqqqqqqqqqqqqq", "qqqqqqqqqqq");
//        if(account)
//        {
//            System.out.println("success");            
//        }
//        else
//        {
//            System.out.println("failed");        
//        }
//    } 
//    public static void main(String[] args) {
//        DAOAccount dao = new DAOAccount();
//        Account account = dao.checkLogin("minh");
//        System.out.println(account);
//    } 
//    public static void main(String[] args) {
//        DAOAccount dao = new DAOAccount();
//        Account account = dao.Login("minh", "123");
//        System.out.println(account);
//    }    
//    public static void main(String[] args) {
//        DAOAccount dao = new DAOAccount();
//        List<Account> Account = dao.getAllAccount();
//        for(Account category : Account)
//        {
//            System.out.println(category);
//        }
//    }
    public static void main(String[] args) {
        DAOAccount dao = new DAOAccount();
        Account account = dao.getAccountByUID(75);
        System.out.println(account);
    }

}
