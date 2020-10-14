package com.westar.demo.be.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "car")
public class Car {


    //汽车： 型号，牌照号， 载人数。

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String model;
    @NonNull
    private String license;
    private Integer seat;
    private String name;
    private String remark;
}