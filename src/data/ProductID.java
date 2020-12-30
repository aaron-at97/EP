package data;
import static java.lang.Character.*;

final public class ProductID {
    private final String productCode;

    public ProductID(String code){
        this.productCode = code;
    }

    public String getProductID() throws Exception{
        if(!CompProductCode())
            throw new Exception("The code is not valid. \n");
        return productCode;
    }

    public Boolean CompProductCode(){

        if (productCode == null)
            return false;

        char [] codeArray = productCode.toCharArray();
        if (productCode.length() == 12) {

            for (int i = 0; i < 12; i++) {
                if (!isDigit(codeArray[i])) {
                    return false;
                }
            }
        }
        else {
            return false;
        }

        return true;
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