/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;



import DTO.CatalogueDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CatalogueDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docDSNCC() throws Exception {
        ArrayList<CatalogueDTO> dsncc = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM productline";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    CatalogueDTO  ncc = new CatalogueDTO();
                    ncc.setCatalogueID(rs.getString("product_line_id"));
                    ncc.setname(rs.getString("name"));
                    ncc.setPrice(rs.getDouble("price"));
                    ncc.setStock(rs.getInt("stock"));
                    ncc.setTrangThai(rs.getString("TrangThai"));
                    dsncc.add(ncc);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Can not read data from Catalogue");
        } 
        return dsncc;   
    }
    public void them(CatalogueDTO ncc ) {
        try{
            String qry ="INSERT INTO productline values(";
            qry = qry+"'"+ncc.getCatalogueID()+"'";
            qry = qry+","+"'"+ncc.getname()+"'";
            qry = qry+","+"'"+ncc.getPrice()+"'";
            qry = qry+","+"'"+ncc.getStock()+"'";
            qry = qry+","+"'"+ncc.getTrangThai()+"'";
            qry = qry+")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
    
    public void sua(CatalogueDTO ncc ){
        try{
             String qry="Update productline Set ";
                    qry = qry + "name=" + "'" + ncc.getname() + "'";
                    qry = qry + ",price=" + "'" + ncc.getPrice() + "'";
                    qry = qry + ",stock=" + "'" + ncc.getStock() + "'";               
                    qry = qry + " " + "where product_line_id ='" + ncc.getCatalogueID() + "'";
                    connection.getStatement();
                    connection.ExecuteUpdate(qry);
                    System.out.println(qry);
                    connection.closeConnect();     
        }catch (Exception ex){
            
        }
    }
    
    public void xoa(String  IDNhaCungCap){
        try {
            String qry = "Update productline Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where product_line_id ='" + IDNhaCungCap + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }
    public void xoa(CatalogueDTO ncc) {
        try {
            String qry = "Update productline Set ";
            qry = qry + "TrangThai=" + "'" + ncc.getTrangThai() + "'";
            qry = qry + " " + "where product_line_id ='" + ncc.getCatalogueID() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }
}

























