package domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductLink {
    private String storeName;
    private String url;

    public ProductLink(String storeName, String url) {
        this.storeName = storeName;
        this.url = url;
    }

    public ProductLink() {
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ProductLink{" +
                "storeName='" + storeName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
