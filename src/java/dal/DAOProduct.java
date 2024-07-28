/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dal.DBContext;
import model.Category;
import model.Product;
/**
 *
 * @author nhat minh
 */
public class DAOProduct extends DBContext{
    public List<Product> getAllProductsByCID( int cid) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "Select * from Product where cateID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImage(rs.getString(3));
                product.setPrice(rs.getFloat(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from product where product.price > 150";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImage(rs.getString(3));
                product.setPrice(rs.getFloat(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Product> getAllProductsWithPasges(int page, int pagesize) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from product where product.price <170"
                    + " order by id\n" +
                         "Offset (?-1)*? rows fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImage(rs.getString(3));
                product.setPrice(rs.getFloat(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int getTotalProduct()
    {
        int list;
        String sql = "select count(id) from Product";
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
    
    public Product getProductByID(int id)
    {
        String sql = "select * from product where id = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImage(rs.getString(3));
                product.setPrice(rs.getFloat(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                return product;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Product> getRandomProduct()
    {
        List<Product> list = new ArrayList<>(); 
       String sql = "SELECT TOP 4 * FROM product ORDER BY newID();";
       try
       {
           PreparedStatement stm = connection.prepareStatement(sql);
           ResultSet rs = stm.executeQuery();
           while(rs.next())
           {
               Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImage(rs.getString(3));
                product.setPrice(rs.getFloat(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
           }
           return list;
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
           return null;
       }
    }
    public List<Product> getProductByName(String name)
    {
        String sql = "select * from product where name like ?";
        List<Product> list = new ArrayList<>();
        try
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImage(rs.getString(3));
                product.setPrice(rs.getFloat(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
            return list;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean insertProduct(String name, String image, float price, String title, String description, int cateID, int sell_ID)
    {
        String sql = "INSERT INTO [dbo].[product]\n" +
"           ([name]\n" +
"           ,[image]\n" +
"           ,[price]\n" +
"           ,[title]\n" +
"           ,[description]\n" +
"           ,[cateID]\n" +
"           ,[sell_ID])\n" +
"     VALUES\n" +
"	(?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, image);
            stm.setFloat(3, price);
            stm.setString(4, title);
            stm.setString(5, description);
            stm.setInt(6, cateID);
            stm.setInt(7, sell_ID);
            stm.executeUpdate(); 
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
  
    public boolean deleteProduct(int id)
    {
        String sql = "delete from product where id = ?";
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
    
    public boolean updateProduct(String name, String image, float price, String title, String description, int cateID, int sell_ID, int id)
    {
        String sql = "UPDATE [dbo].[product]\n" +
                        "   SET [name] = ?\n" +
                        "      ,[image] = ?\n" +
                        "      ,[price] = ?\n" +
                        "      ,[title] = ?\n" +
                        "      ,[description] = ?\n" +
                        "      ,[cateID] = ?\n" +
                        "      ,[sell_ID] = ?\n" +
                        " WHERE [id] = ?";
        try
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, image);
            stm.setFloat(3, 100);
            stm.setString(4, title);
            stm.setString(5, description);
            stm.setInt(6, cateID);
            stm.setInt(7, sell_ID);
            stm.setInt(8, id);
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
//        DAOProduct dao = new DAOProduct();
//        boolean result = dao.updateProduct("sjahgdjhgsad","usahdkjsahdk",77777,"jsadhksahd","jshdkjashd",1,1,66);
//        if(result)
//        {
//            System.out.println("Update success " + result);
//        }
//        else
//        {
//            System.out.println("Update failed " + result);
//        }
//    }
//    public static void main(String[] args) {
//        DAOProduct dao = new DAOProduct();
//        boolean result = dao.deleteProduct(1);
//        if(result)
//        {
//            System.out.println("delete success " + result);
//        }
//        else
//        {
//            System.out.println("delet failed " + result);
//        }
//    }
    
   public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        boolean result = dao.insertProduct("Test Product", "test_image.jpg", 99, "Test Title", "Test Description", 1, 1);
        if(result)
        {
           System.out.println("Insert Product Result: " + result);
        }
        else
        {
            System.out.println("Insert failed " + result);
        }
    }  
//            public static void main(String[] args) {
//            DAOProduct dao = new DAOProduct();
//            List<Product> product = dao.getProductByName("a");
//            for (Product product1 : product) {
//                System.out.println(product1);
//        }
//    }
            
            
//        public static void main(String[] args) {
//            DAOProduct dao = new DAOProduct();
//            List<Product> product = dao.getRandomProduct();
//            for (Product product1 : product) {
//                System.out.println(product1);
//        }
//    }
//    public static void main(String[] args) {
//        DAOProduct dao = new DAOProduct();
//        Product product = dao.getProductByID(1);
//            System.out.println(product);
//    }
//        public static void main(String[] args) {
//        DAOProduct dao = new DAOProduct();
//        int product = dao.getTotalProduct();
//            System.out.println(product);
//    }
//    public static void main(String[] args) {
//        DAOProduct dao = new DAOProduct();
//        List<Product> product = dao.getAllProductsWithPasges(1,10);
//        for (Product product1 : product) {
//            System.out.println(product1);
//        }
//    }
//    public static void main(String[] args) {
//        DAOProduct dao = new DAOProduct();
//        List<Product> product = dao.getAllProductsByCID(1);
//        for (Product product1 : product) {
//            System.out.println(product1);
//        }
//    }
//    public static void main(String[] args) {
//        DAOProduct dao = new DAOProduct();
//        List<Product> product = dao.getAllProducts();
//        for (Product product1 : product) {
//            System.out.println(product1);
//        }
//    }
}
