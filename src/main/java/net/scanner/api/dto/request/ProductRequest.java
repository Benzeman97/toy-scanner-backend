package net.scanner.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


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
    private String offerExpDate;
    private String productImg;
    private String brandImg;
    private String platformImg;
    private String brandName;
    private String prodUrl;
    private String platform;
    private String itemUrl;
    private String offerType;
    private BigDecimal sellingRate;
    private String forValue;
    private int ctgId;
}
