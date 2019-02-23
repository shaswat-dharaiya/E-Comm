/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vvp.java;

import static com.vvp.java.Products.products;
import java.util.HashMap;

/**
 *
 * @author Shaswat's Pc
 */
public class Products
{

    public int pid,stock;
    public String images,productName;
    public double price;
    public int qnty;
    
    public Products(){}
  
    public Products(int pid, int stock, double price, String productName)
    {
        this.pid=pid;
        this.stock=stock;
        this.price=price;
        this.productName=productName;
    }
    
    static HashMap<Integer,Products> products = new HashMap<Integer,Products>();
    
    public static void initData()
    {
        Products p1 = new Products(1,9,400.00f,"Book(s)");
        Products p2 = new Products(2,9,30.00f,"Pen(s)");
        products.put(new Integer(1), p1);
        products.put(new Integer(2), p2);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public static HashMap<Integer, Products> getProducts() {
        return products;
    }

    public static void setProducts(HashMap<Integer, Products> products) {
        Products.products = products;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName=productName;
    }
    public int getPid(){
        return pid;
    }
    public void setPid(int pid){
        this.pid=pid;
    }    
}
