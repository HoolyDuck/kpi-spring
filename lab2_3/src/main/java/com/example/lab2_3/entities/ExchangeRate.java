package com.example.lab2_3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "date")
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @ManyToOne(optional = false)
    @JoinColumn(name = "sourceCurrency_id", referencedColumnName = "id", nullable = false)
    private Currency sourceCurrency;
    @ManyToOne(optional = false)
    @JoinColumn(name = "targetCurrency_id", referencedColumnName = "id", nullable = false)
    private Currency targetCurrency;
    @Column(precision=10, scale=2)
    private double rate;
}
