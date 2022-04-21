/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DTO.*;
import GUI.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Nguyen
 */
public class Tool {

    public static String IDNhanVienHienHanh = "";
    
    public static boolean isNumber(String number) { //kt có phải là số không
        try {
            if (Double.parseDouble(number) < 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean haveSpace(String string)
    {
        if(string.contains(" "))
        {
            return true;
        }
        return false;
    }
    
    public static boolean haveNumber(String string)
    {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
public static boolean isGmail(String gmail)
{
    Pattern pattern = Pattern.compile("^[\\w.+\\-]+@gmail\\.com$");
        Matcher matcher = pattern.matcher(gmail);
        if (matcher.find()) {
            return true;
        }
        return false;
    
}
    public static boolean ngayBDTruocNgayKT(String ngayBatDau, String ngayKetThuc)
    {
        LocalDate ngayTruoc = LocalDate.parse(ngayBatDau);
        LocalDate ngaySau = LocalDate.parse(ngayKetThuc);
        if(ngayTruoc.isBefore(ngaySau))
        {
            return true;
        }
        return false;
    }
    
    public static boolean isTenThousandToOneMil(String donGia)
    {
        try
        {
            if(Integer.parseInt(donGia) > 1000000 || Integer.parseInt(donGia) < 10000)
            {
                return false;
            }

        }catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    public static boolean isLength50(String ten)
    {
        if(ten.length() > 50 || ten.length() < 1)
        {
            return false;
        }
        return true;
    }
    public static boolean isOneToOneThousand(String donGia)
    {

        try
        {
            if(Integer.parseInt(donGia) > 1000 || Integer.parseInt(donGia) < 1)
            {
                return false;
            }
        }catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    public static boolean isHinhAnh(String hinhAnh)
    {
        if(hinhAnh.contains(".png") || hinhAnh.contains(".jpg"))
        {
            return true;
        }
        return false;
    }
    public static boolean isSpecialChar(String specialChar) {  //kiểm tra có chứa ký tự đặc biệt k
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
        Matcher matcher = pattern.matcher(specialChar);
        if (matcher.find()) {
            return true;
        }
        return false;

    }
    
    public static boolean isName(String name)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return false;
        }
        return true;
        
    }
    public static boolean isTongTien(String name)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9. ]");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return false;
        }
        return true;
        
    }
    
    public static boolean isCongThuc(String congThuc)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9, ]");
        Matcher matcher = pattern.matcher(congThuc);
        if (matcher.find()) {
            return false;
        }
        return true;
        
    }
    
    public static boolean isPhoneNumber(String number) //số điện thoại có số đầu là 03 2-9 || 05 678 || 07 06-9 09 0-44-6
    {
        Pattern pattern = Pattern.compile("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            return true;
        }
        return false;
        
    }
    
    public static boolean isMa(String ma)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(ma);
        if (matcher.find()) {
            return false;
        }
        return true;
        
    }
    
    public static boolean isDate(String date)
    {
        Pattern pattern = Pattern.compile("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}");
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            return true;
        }
        return false;
        
    }
    

    public static String chuanHoa(String string) {
        string = string.trim();
        string = string.replaceAll("\\s+", " ");
        return string;
    }

    public static boolean isDuplicateMaMonAn(String string ) //kiểm tra trùng nhau hay không
    {
        for(int i=0;i< SanPhamBUS.ds.size();i++ )
        {
           if(SanPhamBUS.ds.get(i).getIDMonAn().contains(string))
               return true;
        }
        return false;
    }
    
    public static boolean isDuplicateMaNhanVien(String string ) //kiểm tra trùng nhau hay không
    {
        for(int i=0;i< NhanVienBUS.ds.size();i++ )
        {
           if(NhanVienBUS.ds.get(i).getIDNhanVien().contains(string) && NhanVienBUS.ds.get(i).getTrangThai().equals("Hiện"))
               return true;
        }
        return false;
    }
    
    public static String chuanHoaDanhTuRieng(String string) {
        string = chuanHoa(string);
        String temp[] = string.split(" ");
        string = ""; // ? ^-^
        for (int i = 0; i < temp.length; i++) {
            string += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) //
            {
                string += " ";
            }
        }
        return string;
    }

    public static ImageIcon showIcon(int width, int height, String fileName) {
        ImageIcon icon;
        try {
            BufferedImage image = ImageIO.read(new File(fileName));
            icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
            return icon;
        } catch (IOException e) {
            return null;
        }
    }
    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }
    public static ArrayList<MonAnDTO> searchMA(String value, double donGiaTu, double donGiaDen, int soLuongTu, int soLuongDen) { //có thay đổi
        // phương pháp tìm từ arraylist
        ArrayList<MonAnDTO> result = new ArrayList<>();
        //Tìm theo tên
        SanPhamBUS.ds.forEach((MonAnDTO) -> {
            if ( MonAnDTO.getTenMonAn().toLowerCase().contains(value.toLowerCase()) && MonAnDTO.getTrangThai().equals("Hiện") //Tìm kiếm theo chuỗi thường
                || removeAccent(MonAnDTO.getTenMonAn().toLowerCase()).contains(removeAccent(value.toLowerCase())) && MonAnDTO.getTrangThai().equals("Hiện") )//Tìm kiếm theo chữ VN
            {
                result.add(MonAnDTO);
            }
        });

            for (int i = result.size() - 1; i >= 0; i--)
            {
                MonAnDTO monAn = result.get(i);
                double donGia = monAn.getDonGia();
                int soLuong = monAn.getSoLuong();
                Boolean donGiaKhongThoa = (donGiaTu != -1 && donGia < donGiaTu ) || (donGiaDen != -1 && donGia > donGiaDen);
                Boolean soLuongKhongThoa = (soLuongTu != -1 && soLuong < soLuongTu ) || (soLuongDen != -1 && soLuong > soLuongDen );
                if (donGiaKhongThoa || soLuongKhongThoa)
                {
                    result.remove(i);
                }
            }
        return result;
    }
    
    public static ArrayList<NhanVienDTO> searchNV(String value,String type) { 
        // phương pháp tìm từ arraylist
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        if(type=="Mã nhân viên")
        //Tìm theo tên
        NhanVienBUS.ds.forEach((NhanVienDTO) -> {
            if ( NhanVienDTO.getIDNhanVien().toLowerCase().contains(value.toLowerCase()) && NhanVienDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường
            {
                result.add(NhanVienDTO);
            }
        });
        if(type=="Họ")
        //Tìm theo tên
        NhanVienBUS.ds.forEach((NhanVienDTO) -> {
            if ( NhanVienDTO.getHoNhanVien().toLowerCase().contains(value.toLowerCase()) && NhanVienDTO.getTrangThai().equals("Hiện") //Tìm kiếm theo chuỗi thường
                ||removeAccent(NhanVienDTO.getHoNhanVien().toLowerCase()).contains(removeAccent(value.toLowerCase())) && NhanVienDTO.getTrangThai().equals("Hiện"))//Tìm kiếm theo chữ VN 
            {
                result.add(NhanVienDTO);
            }
        });
        if(type=="Tên")
        //Tìm theo tên
        NhanVienBUS.ds.forEach((NhanVienDTO) -> {
            if ( NhanVienDTO.getTenNhanVien().toLowerCase().contains(value.toLowerCase()) && NhanVienDTO.getTrangThai().equals("Hiện") //Tìm kiếm theo chuỗi thường
                ||removeAccent(NhanVienDTO.getTenNhanVien().toLowerCase()).contains(removeAccent(value.toLowerCase())) && NhanVienDTO.getTrangThai().equals("Hiện"))//Tìm kiếm theo chữ VN 
            {
                result.add(NhanVienDTO);
            }
        });
        if(type=="Gmail")
        //Tìm theo tên
        NhanVienBUS.ds.forEach((NhanVienDTO) -> {
            if ( NhanVienDTO.getGmail().toLowerCase().contains(value.toLowerCase()) && NhanVienDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường
                
            {
                result.add(NhanVienDTO);
            }
        });
        if(type=="Giới tính")
        //Tìm theo tên
        NhanVienBUS.ds.forEach((NhanVienDTO) -> {
            if ( NhanVienDTO.getGioiTinh().toLowerCase().contains(value.toLowerCase()) && NhanVienDTO.getTrangThai().equals("Hiện") //Tìm kiếm theo chuỗi thường
                ||removeAccent(NhanVienDTO.getGioiTinh().toLowerCase()).contains(removeAccent(value.toLowerCase())) && NhanVienDTO.getTrangThai().equals("Hiện"))//Tìm kiếm theo chữ VN 
            {
                result.add(NhanVienDTO);
            }
        });
        if(type=="SĐT")
        //Tìm theo tên
        NhanVienBUS.ds.forEach((NhanVienDTO) -> {
            if ( NhanVienDTO.getSoDienThoai().toLowerCase().contains(value.toLowerCase()) && NhanVienDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường                
            {
                result.add(NhanVienDTO);
            }
        });
        if(type=="Chức vụ")
        //Tìm theo tên
        NhanVienBUS.ds.forEach((NhanVienDTO) -> {
            if ( NhanVienDTO.getChucVu().toLowerCase().contains(value.toLowerCase()) && NhanVienDTO.getTrangThai().equals("Hiện") //Tìm kiếm theo chuỗi thường
                    || removeAccent(NhanVienDTO.getChucVu().toLowerCase()).contains(removeAccent(value.toLowerCase())) && NhanVienDTO.getTrangThai().equals("Hiện"))
                
            {
                result.add(NhanVienDTO);
            }
        });
            
        return result;
    }
    
    public static ArrayList<TaiKhoanDTO> searchTK(String value,String type) { 
        // phương pháp tìm từ arraylist
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        if(type=="Tài khoản")
        //Tìm theo tài khoản
        TaiKhoanBUS.ds.forEach((TaiKhoanDTO) -> {
            if ( TaiKhoanDTO.getTaiKhoan().toLowerCase().contains(value.toLowerCase()) && TaiKhoanDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường
            {
                result.add(TaiKhoanDTO);
            }
        });
        if(type=="Mã nhân viên")
        //Tìm theo mã nhân viên
        TaiKhoanBUS.ds.forEach((TaiKhoanDTO) -> {
            if ( TaiKhoanDTO.getIDNhanVien().toLowerCase().contains(value.toLowerCase()) && TaiKhoanDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường
                
            {
                result.add(TaiKhoanDTO);
            }
        });
        
        if(type=="Mã quyền")
        //Tìm theo tên
        TaiKhoanBUS.ds.forEach((TaiKhoanDTO) -> {
            if ( TaiKhoanDTO.getIDPhanQuyen().toLowerCase().contains(value.toLowerCase()) && TaiKhoanDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường                
            {
                result.add(TaiKhoanDTO);
            }
        });
        if(type=="Mật khẩu")
        //Tìm theo mật khẩu
        TaiKhoanBUS.ds.forEach((TaiKhoanDTO) -> {
            if ( TaiKhoanDTO.getMatKhau().toLowerCase().contains(value.toLowerCase()) && TaiKhoanDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường
                
            {
                result.add(TaiKhoanDTO);
            }
        });
        
           
        return result;
    }
    public static ArrayList<PhanQuyenDTO> searchPQ(String value,String type) { 
        // phương pháp tìm từ arraylist
        ArrayList<PhanQuyenDTO> result = new ArrayList<>();
        if(type=="Mã quyền")
        //Tìm theo tên
        PhanQuyenBUS.ds.forEach((PhanQuyenDTO) -> {
            if ( PhanQuyenDTO.getIDPhanQuyen().toLowerCase().contains(value.toLowerCase()) && PhanQuyenDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường
            {
                result.add(PhanQuyenDTO);
            }
        });
        if(type=="Tên quyền")
        //Tìm theo tên
        PhanQuyenBUS.ds.forEach((PhanQuyenDTO) -> {
            if ( PhanQuyenDTO.getTenQuyen().toLowerCase().contains(value.toLowerCase()) && PhanQuyenDTO.getTrangThai().equals("Hiện") //Tìm kiếm theo chuỗi thường
                ||removeAccent(PhanQuyenDTO.getTenQuyen().toLowerCase()).contains(removeAccent(value.toLowerCase())) && PhanQuyenDTO.getTrangThai().equals("Hiện"))//Tìm kiếm theo chữ VN 
            {
                result.add(PhanQuyenDTO);
            }
        });
        
        if(type=="Mô tả quyền")
        //Tìm theo tên
        PhanQuyenBUS.ds.forEach((PhanQuyenDTO) -> {
            if ( PhanQuyenDTO.getMoTaQuyen().toLowerCase().contains(value.toLowerCase()) && PhanQuyenDTO.getTrangThai().equals("Hiện")) //Tìm kiếm theo chuỗi thường                
            {
                result.add(PhanQuyenDTO);
            }
        });
        
           
        return result;
            
    }
    public static ArrayList<MonAnDTO> searchBH(String value) { 
        // phương pháp tìm từ arraylist
        ArrayList<MonAnDTO> result = new ArrayList<>();       
        
        //Tìm theo tên
        SanPhamBUS.ds.forEach((MonAnDTO) -> {
            if ( MonAnDTO.getTenMonAn().toLowerCase().contains(value.toLowerCase()) && MonAnDTO.getTrangThai().equals("Hiện") //Tìm kiếm theo chuỗi thường
                    || removeAccent(MonAnDTO.getTenMonAn().toLowerCase()).contains(removeAccent(value.toLowerCase())) && MonAnDTO.getTrangThai().equals("Hiện") )//Tìm kiếm theo chữ VN
            {
                result.add(MonAnDTO);
            }
        });
            
        return result;
    }
    
    public static boolean docDB() throws Exception
    {
        try
        {
            SanPhamBUS.docDB();
            NhanVienBUS.docDB();
            PhanQuyenBUS.docDB();
            TaiKhoanBUS.docDB();
            
        }catch(NullPointerException e){
        e.printStackTrace();
        return false;
        }  
        return true;
    }
    //Hàm lấy ngày hiện tại 
    public static LocalDate getNgayLap()
    {
        LocalDate ngayLap = java.time.LocalDate.now();
            return ngayLap;
    }
    //Hàm lấy giờ hiện tại
    public static LocalTime getGioLap()
    {
        LocalTime gioLap = java.time.LocalTime.now();
        return gioLap;
    }
    //Hàm tăng mã tự động khi thêm mới
    public static String tangMa(String ma)
    {
        //VD: HD12
        if(ma != null)
        {
            String maSo = ma.substring(2);//lấy 12
            String maChu = ma.substring(0, 2); //lấy ds
            int maSo1= Integer.parseInt(maSo)+ 1;//tăng 12+1
            String maMoi = maChu+ maSo1;
            return maMoi;  
        }
        return null;
    }
    //Hàm tăng mã đặt biệt khi có đến 3 kí tự 
    public static String tangMa3(String ma)
    {
        //VD: NCC12
        if(ma != null)
        {
        String maChu = ma.substring(0, 3); //lấy NCC
        String maSo = ma.substring(3);//lấy 12
        int maSo1= Integer.parseInt(maSo)+ 1;//tăng 12+1
        String maMoi = maChu+ maSo1;
        return maMoi;
        }
        return null;
    }
    public static int getNumberInString(String s){
        int a=0;
        String b="";
        for(int i=0;i<s.length();i++)
            if((((int)s.charAt(i))<48||((int)s.charAt(i))>57)&&((int)s.charAt(i))!=46)
            {
                b=s.substring(0, i);
                
                break;
            }
        b=b.replaceAll("\\W","");
        System.out.println(b);
        a=Integer.parseInt(b);
        return a;
    }
}











