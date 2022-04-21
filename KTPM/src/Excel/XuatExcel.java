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

            SanPhamBUS monanBUS = new SanPhamBUS(); //Tạo biến monanBUS
            ArrayList<MonAnDTO> list = monanBUS.getMonAnDTO();  // tạo danh sách lấy từ DTO thông qua BUS

            int rownum = 0; //cột thứ 0
            Row row = sheet.createRow(rownum); //tạo biến row (hàng) trong sheet
//createCell(int cột, CellType."kiểu dữ liệu") row.createCell (hàng row, tạo cột) 
            row.createCell(0, CellType.STRING).setCellValue("ID"); //Hàng 0. cột 0- kiểu String, giá trị ID
            row.createCell(1, CellType.STRING).setCellValue("Tên món"); //Hàng 0. cột 1- kiểu String, giá trị Tên món
            row.createCell(2, CellType.STRING).setCellValue("Đơn vị tính");//Hàng 0. cột 2- kiểu String, giá trị Đơn vị tính
            row.createCell(3, CellType.STRING).setCellValue("Giá");//Hàng 0. cột 3- kiểu String, giá trị Giá
            row.createCell(4, CellType.STRING).setCellValue("Hình Ảnh");//Hàng 0. cột 4- kiểu String, giá trị Hình Ảnh
            row.createCell(5, CellType.STRING).setCellValue("Loại");//Hàng 0. cột 5- kiểu String, giá trị Loại
            row.createCell(6, CellType.STRING).setCellValue("Số lượng");//Hàng 0. cột 6- kiểu String, giá trị Số lượng
//Tạo vòng lập for chạy hết giá trị của list
            for (MonAnDTO ma : list) {
                rownum++; //rownum (tăng lên giá trị, lúc nãy là 0 giờ là 1 - hàng thứ 1)
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(ma.getIDMonAn()); 
                row.createCell(1, CellType.STRING).setCellValue(ma.getTenMonAn());
                row.createCell(2, CellType.STRING).setCellValue(ma.getDonViTinh());
                row.createCell(3, CellType.NUMERIC).setCellValue(ma.getDonGia()); // Cột 3- kiểu NUMERIC, giá trị chuyển sang String
                row.createCell(4, CellType.STRING).setCellValue(ma.getHinhAnh());
                row.createCell(5, CellType.STRING).setCellValue(ma.getLoai());
                row.createCell(6, CellType.NUMERIC).setCellValue(ma.getSoLuong());
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
    
    private String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
}
    


