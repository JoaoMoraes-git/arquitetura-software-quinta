import domain.Product;

void main() {
    Product product = new Product();
    product.setName("Celular");
    product.setSku("P5P25TN23");
    product.setPrice(new BigDecimal("1000"));
    product.setPrice(new BigDecimal("999"));

    System.out.println(product.toString());

    Product p1 = new Product("Tablet", "F92UFJO1", new BigDecimal("2149.99"));
    Product p2 = new Product("Carregador", "JFI31SF", new BigDecimal("149.99"));
    Product p3 = new Product("Televisão", "KS10I4", new BigDecimal("1999.49"));

    p1.setPrice(new BigDecimal("1940.00"));

    p2.setPrice(new BigDecimal("129.99"));
    p2.setPrice(new BigDecimal("119.99"));

    p3.setPrice(new BigDecimal("1750.00"));
    p3.setPrice(new BigDecimal("1899.00"));
    p3.setPrice(new BigDecimal("1699.00"));

    System.out.println(p1.toString());
    System.out.println(p2.toString());
    System.out.println(p3.toString());
}
