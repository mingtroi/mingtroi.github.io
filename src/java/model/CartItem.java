/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nhat minh
 */
public class CartItem {
    private int cartid;
    private int accountid;
    private int productid;
    private int amount;
    private Product product;

    public CartItem(int amount, Product product) {
        this.amount = amount;
        this.product = product;
    }
    

    public CartItem(int cartid, int accountid, int productid, int amount, Product product) {
        this.cartid = cartid;
        this.accountid = accountid;
        this.productid = productid;
        this.amount = amount;
        this.product = product;
    }
    
    public CartItem() {
    }

    public CartItem(int cartid, int accountid, int productid, int amount) {
        this.cartid = cartid;
        this.accountid = accountid;
        this.productid = productid;
        this.amount = amount;
    }
    
    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartItem{" + "cartid=" + cartid + ", accountid=" + accountid + ", productid=" + productid + ", amount=" + amount + ", product=" + product + '}';
    }

}
