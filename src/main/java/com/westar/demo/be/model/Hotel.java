package com.westar.demo.be.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {


    //酒店： 酒店名字，地址， 地理位置。

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String address;
    private String gpsLatitude; //in decimal degrees
    private String gpsLongitude; //in decimal degrees
    private String remark;
}