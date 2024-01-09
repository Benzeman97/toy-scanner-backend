package net.scanner.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse implements Serializable {

    private int productId;
    private String productName;
    private String price;
    private String prevPrice;
    private String discount;
    private int discountPercentage;
    private String material;
    private String shippingCountry;
    private String offerExpDate;
    private String productImg;
    private String brandImg;
    private String platformImg;
    private String brandName;
    private String platformUrl;
    private String platform;
    private String itemUrl;
    private String offerType;
    private String sellingRate;
    private String forValue;
    private int ctgId;
    private int dealId;

}
