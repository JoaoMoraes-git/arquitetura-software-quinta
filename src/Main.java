import adapter.DatabaseStorage;
import domain.Price;
import domain.Product;
import service.ProductService;
import utils.GenerateValue;

void main() {
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
            2f)
    );

    productService.listAll();



}
