package service;

import com.microsoft.playwright.*;
import domain.Product;
import domain.ProductLink;

import java.util.List;

public class CrawlerService {

    private final ProductService productService;

    public CrawlerService(ProductService productService) {
        this.productService = productService;
    }

    public void execute() {
        System.out.println("--Rodando o Crawler de Preço--");

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"));

            List<Product> products = productService.listAll();

            if (products.isEmpty()) {
                System.out.println("Nenhum produto cadastrado para monitorar.");
                return;
            }

            for (Product product : products) {
                System.out.println("\nVerificando produto: " + product.getName());

                Float lowestPrice = null;
                String storeWithLowestPrice = null;

                for (ProductLink link : product.getLinks()) {
                    System.out.println("Acessando loja " + link.getStoreName());

                    try (Page page = context.newPage()) {
                        page.navigate(link.getUrl(), new Page.NavigateOptions().setTimeout(30000));

                        page.waitForTimeout(2000);

                        String selector = getSelectorForStore(link.getStoreName());

                        if (page.locator(selector).count() > 0) {
                            String priceText = page.locator(selector).first().innerText();
                            Float currentPrice = parsePrice(priceText);

                            System.out.println("-Preço encontrado: R$ " + currentPrice);

                            if (lowestPrice == null || currentPrice < lowestPrice) {
                                lowestPrice = currentPrice;
                                storeWithLowestPrice = link.getStoreName();
                            }
                        } else {
                            System.out.println("!!! Não foi possível encontrar o preço nessa página");
                        }
                    } catch (Exception e) {
                        System.out.println("!!! Erro ao acessar link da loja " + link.getStoreName() + ": " + e.getMessage());
                    }
                }

                if (lowestPrice != null) {
                    System.out.println("-Menor preço encontrado: R$ " + lowestPrice + " na loja: " + storeWithLowestPrice);

                    product.setPrice(lowestPrice, storeWithLowestPrice);

                    productService.update(product);
                } else {
                    System.out.println("-Não foi possível coletar preços para este produto nesta rodada.");
                }
            }

            browser.close();
            System.out.println("\n--Crawler Encerrado--");

        } catch (Exception e) {
            System.err.println("Erro crítico na execução do Crawler: " + e.getMessage());
        }
    }

    private String getSelectorForStore(String storeName) {
        return switch (storeName.toLowerCase()) {
            case "amazon" -> ".a-price-whole";
            case "kabum" -> ".finalPrice";
            case "mercadolivre", "mercado livre" -> ".ui-pdp-price__part .andes-money-amount__fraction";
            case "magalu", "magazine luiza" -> "[data-testid='price-value']";
            default -> "data-price";
        };
    }

    private Float parsePrice(String priceText) {
        if (priceText == null || priceText.isEmpty()) return 0.0F;

        String cleaned = priceText.replaceAll("[^0-9,]", "").replace(",", ".");

        return Float.parseFloat(cleaned);
    }
}
