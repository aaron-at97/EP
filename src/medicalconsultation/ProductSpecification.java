package medicalconsultation;

import data.ProductID;

public class ProductSpecification {
    private  ProductID productCode;
    private  String description;
    private float price;


    public ProductSpecification(String descript, ProductID productID, float price) {
        this.description=descript;
        this.productCode=productID;
        this.price=price;
    }

    public ProductID getProductCode() {
        return productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}