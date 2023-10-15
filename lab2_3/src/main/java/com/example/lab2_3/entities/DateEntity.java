package com.example.lab2_3.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "dates")
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(1)
    @Max(31)
    @Column(name = "day", nullable = false)
    private Integer day;

    @Min(1)
    @Max(12)
    @Column(name = "month", nullable = false)
    private Integer month;

    @Min(2000)
    @Max(2100)
    @Column(name = "year", nullable = false)
    private Integer year;
}