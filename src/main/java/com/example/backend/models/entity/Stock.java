package com.example.backend.models.entity;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock extends BaseEntity{

    private Product product;
    private int xs;
    private int s;
    private int m;
    private int l;
    private int xl;
    private int xxl;

    public Stock() {
    }

    @OneToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getXs() {
        return xs;
    }

    public void setXs(int xs) {
        this.xs = xs;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getXl() {
        return xl;
    }

    public void setXl(int xl) {
        this.xl = xl;
    }

    public int getXxl() {
        return xxl;
    }

    public void setXxl(int xxl) {
        this.xxl = xxl;
    }
}
