package com.westar.demo.be.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "HH:mm")
    private Date startTime;
    @JsonFormat(pattern = "HH:mm")
    private Date arrivalTime;
    private Integer type; //From or back
    private String remark;
}