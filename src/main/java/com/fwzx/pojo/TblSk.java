package com.fwzx.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TblSk {
    private Integer id;

    private String stationid;

    private Date datetime;

    private Double winds;

    private Double windd;

}