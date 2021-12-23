package com.ayush.trading.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "nifty")
@Getter
@Setter
public class Nifty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "high")
    private Double high;

    @Column(name = "low")
    private Double low;

    @Column(name = "open")
    private Double open;

    @Column(name = "close")
    private Double close;
}