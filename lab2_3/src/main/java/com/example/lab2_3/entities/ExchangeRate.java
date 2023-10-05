package com.example.lab2_3.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "date_id", referencedColumnName = "id", nullable = false)
    private ExchangeDate validFromExchangeDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    private Currency sourceCurrency;
    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    private Currency targetCurrency;
    @Column(precision=10, scale=2)
    private double rate;
}
