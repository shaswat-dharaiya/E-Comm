/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vvp.java;

/**
 *
 * @author Shaswat's Pc
 */
public class SelectedProduct {
    
    public int pid,qty;
    public SelectedProduct(){}
    
    public SelectedProduct(int pid, int qty)
    {
        this.pid=pid;
        this.qty=qty;
    }
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
}
