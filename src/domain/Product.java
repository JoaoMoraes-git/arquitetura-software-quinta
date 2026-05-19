package domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", length = 36)
    private UUID uuid;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "store_name")
    private String storeName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_price")
    private Date datePrice;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_links", joinColumns = @JoinColumn(name = "product_id"))
    private List<ProductLink> links = new ArrayList<>();

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Price> historicalPrice = new ArrayList<>();

    public Product() {
    }

    public Product(String sku, String name, Float price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public Product(UUID uuid, String sku, String name, Float price) {
        this.uuid = uuid;
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public List<ProductLink> getLinks() {
        return links;
    }

    public void setLinks(List<ProductLink> links) {
        this.links = links;
    }

    public void setPrice(Float price, String storeName) {
        if (this.price != null && this.datePrice != null) {
            Price oldPrice = new Price(this.price, this.datePrice, this.storeName);
            oldPrice.setProduct(this);
            historicalPrice.add(oldPrice);
        }

        this.price = price;
        this.storeName = storeName;
        this.datePrice = new Date();
    }

    public Date getDatePrice() {
        return datePrice;
    }

    public void setDatePrice(Date datePrice) {
        this.datePrice = datePrice;
    }

    public List<Price> getHistoricalPrice() {
        return historicalPrice;
    }

    public void setHistoricalPrice(List<Price> historicalPrice) {
        this.historicalPrice = historicalPrice;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "UUID='" + uuid.toString() +'\'' +
                "Sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", datePrice=" + datePrice +
                ", historicalPrice=" + historicalPrice +
                '}';
    }
}
