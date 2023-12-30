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
public class CategoryAdminResponse implements Serializable {

    private int ctgId;
    private String ctgName;
    private boolean status;
}
