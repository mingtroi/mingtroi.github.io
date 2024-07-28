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
import model.Category;

/**
 *
 * @author nhat minh
 */
public class DAOCategory extends DBContext{
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        try {
            String sql = "select * from Category";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCid(rs.getInt(1));
                category.setCname(rs.getString(2));
                list.add(category);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    public Category getCategoryByID(int cateid)
    {
        try
        {
            String sql = "Select * from Category where cid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cateid);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Category category = new Category();
                category.setCid(rs.getInt(1));
                category.setCname(rs.getString(2));
                return category;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }
        return null;
    }
    
    public boolean insertCategory(String name)
    {
        String sql = "INSERT INTO [dbo].[Category]\n" +
                     "([cname])\n" +
                     "VALUES\n" +
                     "(?)";
        try 
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.executeUpdate();
            return true;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteCategory(int id)
    {
        String sql = "delete from category where cid = ?";
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
        }
        return false;
    }
    public boolean updateCategory(String name ,int id)
    {
        String sql = "UPDATE [dbo].[Category]\n" +
                "   SET [cname] = ?\n" +
                " WHERE cid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getTotalCategory()
    {
        String sql = "select count(cid) from Category";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }
//    public static void main(String[] args) {
//        DAOCategory daoCategory = new DAOCategory();
//        System.out.println("Testing insertCategory...");
//        boolean insertResult = daoCategory.insertCategory("New Category");
//        System.out.println("Insert result: " + insertResult);
//    }
//    public static void main(String[] args) {
//        DAOCategory daoCategory = new DAOCategory();
//        int categoryId = 1;
//        System.out.println("Testing updateCategory...");
//        boolean updateResult = daoCategory.updateCategory("Updated Category", categoryId);
//        System.out.println("Update result: " + updateResult);
//    }
//    public static void main(String[] args) {
//        DAOCategory daoCategory = new DAOCategory();
//        int categoryId = 9;
//        System.out.println("Testing deleteCategory...");
//        boolean deleteResult = daoCategory.deleteCategory(categoryId);
//        System.out.println("Delete result: " + deleteResult);
//    }
        
//    public static void main(String[] args) {
//        DAOCategory dao = new DAOCategory();
//        List<Category> Category = dao.getCategoryByID(1);
//        System.out.println(Category);
//    }
    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        List<Category> Category = dao.getAllCategories();
        for(Category category : Category)
        {
            System.out.println(category);
        }
    }
}
