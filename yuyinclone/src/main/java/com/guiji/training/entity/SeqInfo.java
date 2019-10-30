package com.guiji.training.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class SeqInfo {
    private String id;
    private String name;
    private int status;
    private String training_done_time;
    private Date create_time;
    private Integer port;
    private String startName;

}
