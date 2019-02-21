package com.syc.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "accounts", type = "person")
public class Account {

    @Id
    private String id;
    private String name;
    private String title;
    private String desc;

}
