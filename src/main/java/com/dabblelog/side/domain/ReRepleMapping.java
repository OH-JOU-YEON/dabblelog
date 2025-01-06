package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ReRepleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reple reple;

    @ManyToOne
    private ReReple reReple;
}
