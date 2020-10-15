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

    @OneToOne
    private Hotel hotel;

    @OneToOne
    private Car car;

    @OneToOne
    private View view;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ScheduleDetail> scheduleDetails;

    private Integer toDuration; //in minutes
    private Integer backDuration; //in minutes

    private String remark;
}