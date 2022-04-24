/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

/**
 *
 * @author Piger Streaming
 */
import EXT.MyTable;
import BUS.*;
import DTO.*;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DocExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Đọc excel", FileDialog.LOAD);

    public DocExcel() {

    }

    public FileDialog getFd() {
        return fd;
    }
    
    private String getFile() {
        fd.setFile("*.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
    
    //Đọc file excel Món ăn
    public void docFileExcelMonAn() {
        fd.setTitle("Nhập dữ liệu món ăn từ excel"); //set Tiêu đề
        String url = getFile(); //tạo file
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream); //Tạo workbook excel mới
            HSSFSheet sheet = workbook.getSheetAt(0); // Tạo sheet excel mới
            Iterator<Row> rowIterator = sheet.iterator(); //Set row trong sheet
            Row row1 = rowIterator.next(); //Tạo biến row
//Tạo biến khi trùng, đếm số lần thêm, ghi đè, bỏ qua
            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
//Tạo cell (ô) - giá trị, 
                    String pdid = cellIterator.next().getStringCellValue(); 
                    String caid = cellIterator.next().getStringCellValue();
                    int purcharsed = (int) cellIterator.next().getNumericCellValue();
                    int ischeck = (int) cellIterator.next().getNumericCellValue();

                    ProductBUS monanBUS = new ProductBUS();
                    ProductDTO monanOld = monanBUS.getMonAnDTO(pdid);

                    if (monanOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "ProductID", "CatalogueID", "Purcharsed", "IsCheck"});
                            mtb.addRow(new String[]{
                                "Cũ:", monanOld.getProductId(),
                                monanOld.getCatalogueID(),
                                String.valueOf(monanOld.getPurcharsed()),
                                String.valueOf(monanOld.getIsCheck())
                            });

                            mtb.addRow(new String[]{
                                "Mới:", pdid, caid, String.valueOf(purcharsed), String.valueOf(ischeck)                            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            ProductDTO DTO=new ProductDTO(pdid, caid,purcharsed,ischeck, "Hiện");
                            monanBUS.sua(DTO,ProductBUS.timViTri(pdid));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        ProductDTO monan = new ProductDTO(pdid, caid,purcharsed,ischeck, "Hiện");
                        monanBUS.them(monan);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    
     //Đọc file excel Nhà cung cấp
    public void docFileExcelNhaCungCap() {
        fd.setTitle("Input data from excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String name = cellIterator.next().getStringCellValue();
                    double price = (double) cellIterator.next().getNumericCellValue();
                    int stock = (int) cellIterator.next().getNumericCellValue();

                    CatalogueBUS nccBUS = new CatalogueBUS();
                    CatalogueDTO nccOld = nccBUS.getNhaCungCapDTO(id);

                    if (nccOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "ID", "Name", "Price", "Stock"});
                            mtb.addRow(new String[]{
                                "Cũ:", nccOld.getCatalogueID(),
                                nccOld.getname(),
                                String.valueOf(nccOld.getPrice()),
                                String.valueOf(nccOld.getStock())
                            });
                            
                            mtb.addRow(new String[]{
                                "Mới:", id, name, String.valueOf(price), String.valueOf(stock)                            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            CatalogueDTO DTO=new CatalogueDTO(id, name, price, stock, "Hiện");
                            nccBUS.sua(DTO,CatalogueBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        CatalogueDTO nhacc = new CatalogueDTO(id, name, price, stock, "Hiện");
                        nccBUS.them(nhacc);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Tài khoản
    public void docFileExcelTaiKhoan() {
        fd.setTitle("Nhập dữ liệu tài khoản từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String idnv = cellIterator.next().getStringCellValue();
                    String idquyen = cellIterator.next().getStringCellValue();
                    String matkhau = cellIterator.next().getStringCellValue();


                    TaiKhoanBUS tkBUS = new TaiKhoanBUS();
                    TaiKhoanDTO tkOld = tkBUS.getTaiKhoanDTO(id);

                    if (tkOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Tài khoản", "Mã nhân viên", "Mã quyền", "Mật khẩu"});
                            mtb.addRow(new String[]{
                                "Cũ:", tkOld.getTaiKhoan(),
                                tkOld.getIDNhanVien(),
                                tkOld.getIDPhanQuyen(),
                                tkOld.getMatKhau(),

                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, idnv, idquyen, matkhau                  
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            TaiKhoanDTO DTO=new TaiKhoanDTO(id, idnv, idquyen, matkhau, "Hiện");
                            tkBUS.sua(DTO,TaiKhoanBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        TaiKhoanDTO taka = new TaiKhoanDTO(id, idnv, idquyen, matkhau, "Hiện");
                        tkBUS.them(taka);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Phân quyền
    public void docFileExcelPhanQuyen() {
        fd.setTitle("Nhập dữ liệu phân quyền từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String mota = cellIterator.next().getStringCellValue();

                    PhanQuyenBUS pqBUS = new PhanQuyenBUS();
                    PhanQuyenDTO pqOld = pqBUS.getPhanQuyenDTO(id);
                    

                    if (pqOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã quyền", "Tên quyền"});
                            mtb.addRow(new String[]{
                                "Cũ:", pqOld.getIDPhanQuyen(),
                                pqOld.getTenQuyen()

                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, ten                          
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            PhanQuyenDTO DTO=new PhanQuyenDTO(id, ten, mota, "Hiện");
                            pqBUS.sua(DTO,PhanQuyenBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        PhanQuyenDTO phaqu = new PhanQuyenDTO(id, ten, mota, "Hiện");
                        pqBUS.them(phaqu);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
}




