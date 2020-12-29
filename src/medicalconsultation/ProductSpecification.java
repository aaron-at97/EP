package medicalconsultation;

import data.ProductID;
import java.math.BigDecimal;


public class ProductSpecification {
    private  String description;
    private BigDecimal price;

    private  ProductID UPCcode;

    public ProductSpecification (ProductID productID){
        this.UPCcode = productID;
    }
    public ProductSpecification(BigDecimal price, String descript, ProductID productID) {
        this.price = price;
        this.description=descript;
        this.UPCcode=productID;
    }

    public String getDescription() {
        return description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setDescription(String des){
        this.description=des;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}