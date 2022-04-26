/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.CatalogueDAO;
import DTO.CatalogueDTO;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class CatalogueBUS {
   public static ArrayList<CatalogueDTO> ds;
   public static CatalogueDAO DAO=new CatalogueDAO();
   public CatalogueBUS()
    {
        
    }
    public  static void  docDB() throws Exception 
    {
        
        if(ds==null) ds=new ArrayList<CatalogueDTO>();
        ds =DAO.docDSNCC();
    }
    public void them(CatalogueDTO ncc)
    {
        try
        {
            DAO.them(ncc);
            if(ds!=null)
            ds.add(ncc);
        }
        catch (Exception ex) {
           Logger.getLogger(CatalogueBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(CatalogueDTO ncc,int i)
    {
        try
        {
           DAO.sua(ncc);
           if(ds!=null)
           ds.set(i, ncc);
        }
        catch (Exception ex) {
           Logger.getLogger(CatalogueBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     public void xoa(CatalogueDTO ncc,int index)
    {
        String xoancc = ds.get(index).getCatalogueID();
        DAO.xoa(xoancc);
        if(ds!=null)
        ds.set(index,ncc);
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        DAO.xoa(ID); // update trạng thái lên database
        CatalogueDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(ds!=null)
        ds.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getCatalogueID().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public CatalogueDTO getNhaCungCapDTO(String idncc) {
        for (CatalogueDTO nccDTO : ds) {
            if (nccDTO.getCatalogueID().equals(idncc)) {
                return nccDTO;
            }
        }
        return null;
    }

    public ArrayList<CatalogueDTO> getNhaCungCapDTO() {
    return ds;
    }
    
    public static String getMaNhaCungCapCuoi() //lấy mã cuối để tăng
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getCatalogueID();
         return ma;
        }
         return null;
    }
}






