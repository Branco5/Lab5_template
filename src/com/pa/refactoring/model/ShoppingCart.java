package com.pa.refactoring.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author patricia.macedo
 */
public class ShoppingCart{

    private double total;
    private List<Product> products;
    private Date date;
    private boolean terminated;

    public ShoppingCart() {
        total = 0;
        products=new ArrayList<>();
        date = new Date();
        terminated=false;
    }

    public double getTotal() {
       total=0;
        for (Product p : products) {
            total += p.getCost();
        }
        return total;
    }

    public int size(){
        return products.size();
    }

    public void add(Product p){
        products.add(p);
    }

    public List<Product> getProducts(){
        return products;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void terminate(){
        terminated=true;
        this.date = new Date();
    }

    public Date getDate(){
        return date;
    }


}
