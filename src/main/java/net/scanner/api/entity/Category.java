package net.scanner.api.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "toyscanner_db")
@Getter
@Setter
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
    private int ctgId;

    @Column(name = "category_name")
    private String ctgName;
}
