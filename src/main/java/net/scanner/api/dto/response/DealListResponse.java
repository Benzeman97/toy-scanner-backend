package net.scanner.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.scanner.api.entity.Product;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DealListResponse implements Serializable {

    private int dealId;
    private String dealName;
    private String dealType;
    private List<Product> products;
}
