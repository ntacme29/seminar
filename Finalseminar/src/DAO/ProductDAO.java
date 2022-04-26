//main
package DAO;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductDAO {

    ConnectDB connection = new ConnectDB();

    public ProductDAO() {

    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<ProductDTO> dsma = new ArrayList<>();
        try {
            String qry = "SELECT * FROM productinstance";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    ProductDTO monan = new ProductDTO();
                    monan.setProductId(result.getString("product_instance_id"));
                    monan.setCatalogueID(result.getString("product_line_id"));
                    monan.setPurcharsed(result.getInt("product_mount"));
                    monan.setIsCheck(result.getInt("is_check"));
                    monan.setTrangThai(result.getString("TrangThai"));
                    dsma.add(monan);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Can not read data from Product");
        }
        return dsma;
    }

    public void them(ProductDTO monan) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO productinstance values (";
            qry = qry + "'" + monan.getProductId()+ "'";
            qry = qry + "," + "'" + monan.getCatalogueID() + "'";
            qry = qry + "," + "'" + monan.getPurcharsed() + "'";
            qry = qry + "," + "'" + monan.getIsCheck() + "'";
            qry = qry + "," + "'" + monan.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "Update productinstance Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where product_instance_id='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }

    public void xoa(ProductDTO monan) {
        try {
            String qry = "Update productinstance Set ";
            qry = qry + "TrangThai=" + "'" + monan.getTrangThai() + "'";
            qry = qry + " " + "where product_instance_id='" + monan.getProductId() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            connection.closeConnect();
        } catch (Exception e) {
            
        }
    }

    public void sua(ProductDTO monan) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update productinstance Set ";
            qry = qry + "product_line_id=" + "'" + monan.getCatalogueID() + "'";
            qry = qry + ",product_mount=" + "'" + monan.getPurcharsed() + "'";
            qry = qry + ",is_check=" + "'" + monan.getIsCheck() + "'";
            qry = qry + " " + "where product_instance_id='" + monan.getProductId() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }

}














