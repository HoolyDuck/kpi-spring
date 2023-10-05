package com.example.lab2_3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ex_date")
@Setter
@Getter
@AllArgsConstructor
@Builder
public class ExchangeDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date", nullable = false)
    private Date date;

    public ExchangeDate(){

    }
}
