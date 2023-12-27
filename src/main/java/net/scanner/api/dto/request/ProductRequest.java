package net.scanner.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest implements Serializable {

    private String productName;
    private BigDecimal price;
    private BigDecimal prevPrice;
    private String material;
    private String shippingCountry;
    private LocalDateTime offerExpDate;
    private String productImg;
    private String brandImg;
    private String platformImg;
    private String brandName;
    private String prodUrl;
    private String platform;
    private String itemUrl;
    private String offerType;
    private BigDecimal sellingRate;
}
