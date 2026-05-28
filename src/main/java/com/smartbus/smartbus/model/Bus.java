package com.smartbus.smartbus.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    private String location;

    @Enumerated(EnumType.STRING)
    private ModeType modeType = ModeType.AUTO;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Sensor> sensors;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mode_id")
    private Mode mode;
}