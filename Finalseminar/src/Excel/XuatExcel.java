/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;
import BUS.*;
import DTO.*;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Admin
 */
public class XuatExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    public FileDialog getFd() {
        return fd;
    }
    
    private String getFile() {
        fd.setFile("untitled.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
        // Xuất file Excel Món Ăn   
    public void xuatFileExcelMonAn() {
        fd.setTitle("Xuất dữ liệu món ăn ra excel"); //Set tên
        String url = getFile(); //Kiểm tra getfile()
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();//Đọc và ghi file định dạng Microsoft Excel (XLS – định dạng hỗ trợ của Excel 2003) - Workbook: file
            HSSFSheet sheet = workbook.createSheet("Món Ăn");//Tạo bảng tính Món Ăn

            ProductBUS monanBUS = new ProductBUS(); //Tạo biến monanBUS
            ArrayList<ProductDTO> list = monanBUS.getMonAnDTO();  // tạo danh sách lấy từ DTO thông qua BUS

            int rownum = 0; //cột thứ 0
            Row row = sheet.createRow(rownum); //tạo biến row (hàng) trong sheet
//createCell(int cột, CellType."kiểu dữ liệu") row.createCell (hàng row, tạo cột) 
            row.createCell(0, CellType.STRING).setCellValue("Product ID"); //Hàng 0. cột 0- kiểu String, giá trị ID
            row.createCell(1, CellType.STRING).setCellValue("Catalogue ID"); //Hàng 0. cột 1- kiểu String, giá trị Tên món
            row.createCell(2, CellType.STRING).setCellValue("Purcharsed");//Hàng 0. cột 2- kiểu String, giá trị Đơn vị tính
            row.createCell(3, CellType.STRING).setCellValue("Ischeck");//Hàng 0. cột 3- kiểu String, giá trị Giá
//Tạo vòng lập for chạy hết giá trị của list
            for (ProductDTO ma : list) {
                rownum++; //rownum (tăng lên giá trị, lúc nãy là 0 giờ là 1 - hàng thứ 1)
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(ma.getProductId()); 
                row.createCell(1, CellType.STRING).setCellValue(ma.getCatalogueID());
                row.createCell(2, CellType.NUMERIC).setCellValue(ma.getPurcharsed()); // Cột 3- kiểu NUMERIC, giá trị chuyển sang String
                row.createCell(3, CellType.NUMERIC).setCellValue(ma.getIsCheck());
            }
//Tạo vòng lập từ 0 tới rownum để set lại kích thước cột cho gọn
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }
//Tiến hành tạo file và ghi file
            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
  
    
    //Xuất file Excel Nhà Cung Cấp
    public void xuatFileExcelNhaCungCap() {
        fd.setTitle("Export to excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhà Cung Cấp");

            CatalogueBUS nccBUS = new CatalogueBUS();
            ArrayList<CatalogueDTO> list = nccBUS.getNhaCungCapDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("ID");
            row.createCell(1, CellType.STRING).setCellValue("name");
            row.createCell(2, CellType.STRING).setCellValue("price");
            row.createCell(3, CellType.STRING).setCellValue("stock");

            for (CatalogueDTO ncc : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(ncc.getCatalogueID());
                row.createCell(1, CellType.STRING).setCellValue(ncc.getname());
                row.createCell(2, CellType.NUMERIC).setCellValue(ncc.getPrice());
                row.createCell(3, CellType.NUMERIC).setCellValue(ncc.getStock());

            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Complete: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Tài Khoản
    public void xuatFileExcelTaiKhoan() {
        fd.setTitle("Xuất dữ liệu tài khoản ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Tài Khoản");

            TaiKhoanBUS tkBUS = new TaiKhoanBUS();
            ArrayList<TaiKhoanDTO> list = tkBUS.getTaiKhoanDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("ID");
            row.createCell(1, CellType.STRING).setCellValue("Mã Quyền");
            row.createCell(2, CellType.STRING).setCellValue("Mật Khẩu");

            for (TaiKhoanDTO tk : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(tk.getTaiKhoan());
                row.createCell(1, CellType.STRING).setCellValue(tk.getIDPhanQuyen());
                row.createCell(2, CellType.STRING).setCellValue(tk.getMatKhau());


            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Phân Quyền
    public void xuatFileExcelPhanQuyen() {
        fd.setTitle("Xuất dữ liệu phân quyền ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Phân Quyền");

            PhanQuyenBUS pqBUS = new PhanQuyenBUS();
            ArrayList<PhanQuyenDTO> list = pqBUS.getPhanQuyenDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("Mã quyền");
            row.createCell(1, CellType.STRING).setCellValue("Tên quyền");
            

            for (PhanQuyenDTO pq : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(pq.getIDPhanQuyen());
                row.createCell(1, CellType.STRING).setCellValue(pq.getTenQuyen());
                


            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    private String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
}
    


