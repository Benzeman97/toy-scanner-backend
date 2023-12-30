package net.scanner.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandRequest implements Serializable {

    private int brandId;
    private String brandName;
    private String brandImg;
}
