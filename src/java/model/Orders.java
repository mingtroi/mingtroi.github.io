/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nhat minh
 */
public class Orders {
    private int id;
    private int accountid;
    private float totalprice;
    private String note;
    private String createdate;
    private int shippingid;

    public Orders() {
    }

    public Orders(int accountid, float totalprice, String note, int shippingid) {
        this.accountid = accountid;
        this.totalprice = totalprice;
        this.note = note;
        this.shippingid = shippingid;
    }

    public Orders(int id, int accountid, float totalprice, String note, String createdate, int shippingid) {
        this.id = id;
        this.accountid = accountid;
        this.totalprice = totalprice;
        this.note = note;
        this.createdate = createdate;
        this.shippingid = shippingid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getShippingid() {
        return shippingid;
    }

    public void setShippingid(int shippingid) {
        this.shippingid = shippingid;
    }

    @Override
    public String toString() {
        return "Orders{" + "id=" + id + ", accountid=" + accountid + ", totalprice=" + totalprice + ", note=" + note + ", createdate=" + createdate + ", shippingid=" + shippingid + '}';
    }
}
