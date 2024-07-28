/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nhat minh
 */
public class Account {
    private int uid;
    private String user;
    private String pass;
    private int issell;
    private int isadmin;
    private boolean active;

    public Account(int uid, String user, String pass, int issell, int isadmin, boolean active) {
        this.uid = uid;
        this.user = user;
        this.pass = pass;
        this.issell = issell;
        this.isadmin = isadmin;
        this.active = active;
    }

    public Account() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIssell() {
        return issell;
    }

    public void setIssell(int issell) {
        this.issell = issell;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Account{" + "uid=" + uid + ", user=" + user + ", pass=" + pass + ", issell=" + issell + ", isadmin=" + isadmin + ", active=" + active + '}';
    }
    
}
