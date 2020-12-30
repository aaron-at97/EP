package medicalconsultation;

import data.ProductID;

public class ProductSpecification {
    private  ProductID productCode;
    private  String description;


    public ProductSpecification(String descript, ProductID productID) {
        this.description=descript;
        this.productCode=productID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}