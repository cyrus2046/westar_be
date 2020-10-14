package com.westar.demo.be.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    //schedule

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private long hotelId;

    @NonNull
    private long carId;

    @NonNull
    private long viewId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ScheduleDetail> scheduleDetails;

    private Integer duration; //in minutes
    private String remark;
}