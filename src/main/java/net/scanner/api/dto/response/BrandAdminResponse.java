package net.scanner.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandAdminResponse implements Serializable {

    private int brandId;
    private String brandName;
    private boolean status;
}
