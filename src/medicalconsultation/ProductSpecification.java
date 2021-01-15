package medicalconsultation;

import data.ProductID;

import java.util.Objects;

public class ProductSpecification {
    private  ProductID productCode;
    private  String description;
    private float price;
    private String nombre;

    public ProductSpecification(String descript, ProductID productID, float price) {
        this.description=descript;
        this.productCode=productID;
        this.price=price;
    }

    public ProductSpecification(String nombre,String descript, ProductID productID, float price) {
        this.nombre=nombre;
        this.description=descript;
        this.productCode=productID;
        this.price=price;
    }

    public ProductID getProductCode() {
        return productCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecification that = (ProductSpecification) o;
        return Float.compare(that.price, price) == 0 &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(description, that.description);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ProductSpecification{" +
                "productCode=" + productCode +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}