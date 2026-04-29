import domain.Product;
import service.ProductService;
import utils.GenerateValue;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();


        productService.create(new Product(
                GenerateValue.uuid(),
                "SKU",
                "Name",
                2f)
        );

        productService.create(new Product(
                GenerateValue.uuid(),
                "SKU",
                "Name",
                8f)
        );

        productService.listAll();
    }
}
