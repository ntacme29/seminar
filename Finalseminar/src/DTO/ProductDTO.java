package DTO;
//main
/**
 *
 * @author Nguyen
 */
public class ProductDTO {
    private String  ProductId,CatalogueID,TrangThai;
    private int purcharsed,isCheck;

    public ProductDTO(String ProductId, String CatalogueID, int purcharsed, int isCheck, String TrangThai) {
        this.ProductId = ProductId;
        this.CatalogueID = CatalogueID;
        this.purcharsed = purcharsed;
        this.isCheck = isCheck;
        this.TrangThai = TrangThai;
    }
    public ProductDTO(String ProductId, String CatalogueID, int purcharsed, int isCheck) {
        this.ProductId = ProductId;
        this.CatalogueID = CatalogueID;
        this.purcharsed = purcharsed;
        this.isCheck = isCheck;
    }
    
    
    public ProductDTO()
    {
        
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getCatalogueID() {
        return CatalogueID;
    }

    public void setCatalogueID(String CatalogueID) {
        this.CatalogueID = CatalogueID;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getPurcharsed() {
        return purcharsed;
    }

    public void setPurcharsed(int purcharsed) {
        this.purcharsed = purcharsed;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    

}



