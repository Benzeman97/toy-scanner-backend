package net.scanner.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "deal", schema = "toyscanner_db")
@Getter
@Setter
public class Deal {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "deal_id")
    private int dealId;

    @Column(name = "deal_name")
    private String dealName;

    @Column(name = "deal_type")
    private String dealType;

    @Column(name = "launched_date")
    private LocalDateTime launchedDate;

}
