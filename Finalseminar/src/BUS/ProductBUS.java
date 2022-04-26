package BUS;
import DTO.*;
import DAO.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
// copy paste qua hết
public class ProductBUS {

    public static ArrayList<ProductDTO> ds;
    public static ProductDAO data = new ProductDAO();
    public ProductBUS() {

    }

    public static void docDB() throws Exception //cần ghi lại khi qua class khác
    {
        try{
        if (ds == null) {
            ds = new ArrayList<>();
        }
        ds = data.docDB(); // đọc dữ liệu từ database
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng món ăn ở BUS");
        }
    }

    public void them(ProductDTO monAn) //cần ghi lại khi qua class khác
    {
        data.them(monAn);//ghi vào database
        if(ds!=null)
            ds.add(monAn);//cập nhật arraylist
    }

    public void sua(ProductDTO monAn, int i) //cần ghi lại khi qua class khác
    {
        data.sua(monAn);
        if(ds!=null)
            ds.set(i, monAn);
    }

    public void xoa(String ID, int index) //cần ghi lại khi qua class khác
    {
        data.xoa(ID); // update trạng thái lên database
        ProductDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(ds!=null)
            ds.set(index, DTO);
    }
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getProductId().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    public ArrayList<ProductDTO> getMonAnDTO() {
        return ds;
    }
    public ProductDTO getMonAnDTO(String idmonan) {
        for (ProductDTO maDTO : ds) {
            if (maDTO.getProductId().equals(idmonan)) {
                return maDTO;
            }
        }
        return null;
    }
 
    public static String getMaMonAnCuoi()
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getProductId();
         return ma;
        }
         return null;
    }
    
    public static boolean timMaMonAn(String maMonAn)
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        for(ProductDTO monAnDTO : ds)
        {
            if(monAnDTO.getProductId().equals(maMonAn))
            {
                return true;
            }
        }
         return false;
    }
}












