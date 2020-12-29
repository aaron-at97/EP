package data;

final public class ProductID {
    private final String productCode;

    public ProductID(String code){
        this.productCode = code;
    }

    public String getProductID() {
        return productCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductID product = (ProductID) o;
        return productCode.equals(product.productCode);
    }

    @Override
    public int hashCode() { return productCode.hashCode(); }

    @Override
    public String toString() {
        return "ProductID{" + "product code='" + productCode + '\'' + '}';
    }

}