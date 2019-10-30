create schema if not exists training;

create table if not exists training.package
(
    id                 int auto_increment,
    name               varchar(200)  not null,
    status             int default 0 not null comment '状态:0-上传中,1-训练中,2-训练完成,4-启动中 5-启动完成,6-训练失败,7-启动失败,8-上传完成',
    training_done_time timestamp     null,
    create_by          varchar(100)  null comment '创建人',
    create_time        timestamp     null comment '创建时间',
    url                varchar(500)  null comment '文件资源路径',
    port               int           null,
    startName          varchar(32)   null,
    trainTime          int           null,
    total              int           not null comment '音频总条数',
    model_name         varchar(64)   null,
    constraint id_UNIQUE
        unique (id)
)
    ENGINE = InnoDB
    comment '语料包';

alter table training.package
    add primary key (id);



create table if not exists training.file
(
    id          int auto_increment
        primary key,
    name        varchar(50)                        not null,
    text        varchar(500)                       not null comment '音频文字内容',
    url         varchar(500)                       not null comment '文件资源路径',
    package_id  int                                not null,
    create_time datetime default CURRENT_TIMESTAMP not null
)
    ENGINE = InnoDB
    comment '文件';
