/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author nhat minh
 */
public class Cart {
    private List<CartItem> item;
    private float totalPrice;

    public Cart() {
    }

    public Cart(List<CartItem> item, float totalPrice) {
        this.item = item;
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getItem() {
        return item;
    }

    public void setItem(List<CartItem> item) {
        this.item = item;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public boolean checkExist(int id)
    {
        for(CartItem item : item)
        {
            if(item.getProduct().getId() == id)
            {
                return true;
            }
        }
        return false;
    }
    private CartItem getItemsByPid(int id) {
        for (CartItem currentItem : item) { 
            if (currentItem.getProduct().getId() == id) {
                return currentItem;
            }
        }
        return null;
    }

    public void addCart(CartItem newItem)
    {
        if(checkExist(newItem.getProduct().getId()))
        {
           CartItem oldItem = getItemsByPid(newItem.getProduct().getId());
           oldItem.setAmount(oldItem.getAmount() + newItem.getAmount());
        }
        else
        {
            item.add(newItem);
        }
    }
    
    public void changeAmount(int pid, int amount)
    {
        CartItem item = getItemsByPid(pid);
        item.setAmount(amount);
        if(amount <= 0)
        {
            removeItem(pid);
        }
    }
    
    public void removeItem(int pid)
    {
        if(getItemsByPid(pid) != null)
        {
            item.remove(getItemsByPid(pid));
        }
    }
    public float getTotalMoney()
    {
        float total = 0;
        for(CartItem item :item)
        {
            total += (Float) (item.getAmount() * item.getProduct().getPrice());
        }
        return total;
    }
    public int totalAmount()
    {
        int total = 0;
        for(CartItem items :item)
        {
            total += (int) items.getAmount();
        }
        return total;
    }
}
