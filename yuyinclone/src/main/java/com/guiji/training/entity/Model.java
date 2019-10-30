package com.guiji.training.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class Model {
    private int id;
    private String name;
    private Integer status;
    private String training_done_time;
    private String create_by;
    private Date create_time;
    private String url;
    private String startName;
    private String ip;
    private Integer port;
    private String modelName;
    private String startTime;
    private String label;
    private String modelUrl;
}