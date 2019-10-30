package com.guiji.training.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Base64;
import java.util.Date;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/20
 * author: song yong chang
 */
@Data
@AllArgsConstructor
@Entity
@Table
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int status;
    private String trainingDoneTime;
    private String createBy;
    private Date createTime;
    private String url;
    private Integer total;

    public Package() {

    }

    public Package(String name, int status) {
        this.name = name;
        this.status = status;
    }


    public String getName() {
        return new String(Base64.getDecoder().decode(name));
    }

    public void setName(String name) {
        this.name = Base64.getEncoder().encodeToString(name.getBytes());
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", trainingDoneTime=" + trainingDoneTime +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", url='" + url + '\'' +
                ", total=" + total +
                '}';
    }




    public static void main(String[] args) {
        String s = "暮云清寒1566898510851";
        String temp = Base64.getEncoder().encodeToString(s.getBytes());
        System.out.println(temp);

        System.out.println(new String(Base64.getDecoder().decode("6JGj5bCP5aeQ44CC8J+SrQ==")));
    }
}
