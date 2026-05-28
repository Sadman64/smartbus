package com.smartbus.smartbus.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "modes")
public class Mode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MusicType musicType;

    private Double targetTemp;
    private Double targetHumidity;
    private Double targetCo2;

    @Enumerated(EnumType.STRING)
    private ModeType modeType;
}