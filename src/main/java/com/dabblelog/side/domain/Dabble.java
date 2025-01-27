package com.dabblelog.side.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dabble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String yearAndMonth;

    private String title;


    public Dabble(LocalDateTime localDateTime) {

        this.yearAndMonth = localDateTime.format(DateTimeFormatter.ofPattern("yy-MM"));

        this.title = localDateTime.format(DateTimeFormatter.ofPattern("MM")) + "월 회고";
    }




}
