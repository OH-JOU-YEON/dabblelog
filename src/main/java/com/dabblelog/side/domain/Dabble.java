package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "months_dabbles")
public class Dabble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dabble_id")
    private Long id;

    private int Year;

    private int Month;


}
