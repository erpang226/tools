package com.guiji.training.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
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
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String text;
    private String url;
    private Long packageId;
    private Date createTime;

    public File(Long id, String name, String text, String url, Long packageId) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.url = url;
        this.packageId = packageId;
    }

    public File() {

    }
}
