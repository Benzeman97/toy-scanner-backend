package net.scanner.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "toyscanner_db")
@Getter
@Setter
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    private BigDecimal price;

    @Column(name = "prev_price")
    private BigDecimal prevPrice;

    private BigDecimal discount;

    @Column(name = "discount_percentage")
    private Integer discountPercentage;

    private String material;

    @Column(name = "shipping_country")
    private String shippingCountry;

    @Column(name = "offer_exp_date")
    private LocalDateTime offerExpDate;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "brand_img")
    private String brandImg;

    @Column(name = "platform_img")
    private String platformImg;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "platform_url")
    private String platformUrl;

    private String platform;

    @Column(name = "item_url")
    private String itemUrl;

    @Column(name = "offer_type")
    private String offerType;

    @Column(name = "selling_rate")
    private BigDecimal sellingRate;

    @Column(name = "for_value")
    private String forValue;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "inserted_date")
    private LocalDateTime insertedDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
