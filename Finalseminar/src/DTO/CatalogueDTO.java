/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nguyen
 */
public class CatalogueDTO {
    private String CatalogueID,name;
    private double price;
    private int Stock;
    private String TrangThai;

    public CatalogueDTO(String CatalogueID, String name, double price, int Stock, String TrangThai) {
        this.CatalogueID = CatalogueID;
        this.name = name;
        this.price = price;
        this.Stock = Stock;
        this.TrangThai = TrangThai;
    }

    public CatalogueDTO(String CatalogueID, String name, double price, int Stock) {
        this.CatalogueID = CatalogueID;
        this.name = name;
        this.price = price;
        this.Stock = Stock;
    }

    public CatalogueDTO(){
        
    }

    public String getCatalogueID() {
        return CatalogueID;
    }

    public void setCatalogueID(String CatalogueID) {
        this.CatalogueID = CatalogueID;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}









