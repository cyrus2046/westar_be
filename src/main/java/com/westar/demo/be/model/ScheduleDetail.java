package com.westar.demo.be.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(name = "schedule_detail")
public class ScheduleDetail {


    //schedule details: to/back time

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private Date startTime;
    private Date arrivalTime;
    private String remark;
}