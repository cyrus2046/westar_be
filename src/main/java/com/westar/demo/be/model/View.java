package com.westar.demo.be.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "view")
public class View {

    //景点： 景点名字， 地址， 地理位置， 开放时间。

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String address;
    private String gpsLatitude; //in decimal degrees
    private String gpsLongitude; //in decimal degrees
    @JsonFormat(pattern = "HH:mm")
    private Date open;
    @JsonFormat(pattern = "HH:mm")
    private Date close;
    private String remark;
}