package net.scanner.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "brand", schema = "toyscanner_db")
@Getter
@Setter
public class Brand {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "brand_img")
    private String brandImg;

}
