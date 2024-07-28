/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nhat minh
 */
public class OrderDetail {
    private int id;
    private int orderid;
    private String productname;
    private String productimage;
    private float productprice;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderid, String productname, String productimage, float productprice, int quantity) {
        this.id = id;
        this.orderid = orderid;
        this.productname = productname;
        this.productimage = productimage;
        this.productprice = productprice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public float getProductprice() {
        return productprice;
    }

    public void setProductprice(float productprice) {
        this.productprice = productprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", orderid=" + orderid + ", productname=" + productname + ", productimage=" + productimage + ", productprice=" + productprice + ", quantity=" + quantity + '}';
    }
    
}
