# H5 接口
1.获取批次号接口

	request:
	GET /packageId?nickName=hauhjfadsf123 HTTP/1.1
	Host: localhost:28080


	response:
	{
		"code": "0000",
		"msg": "success",
		"data": 1
	}

2.上传单个wav文件信息
    https://gmh5api.guiji.ai/upload/jssdk/282/3GLik2N4gT7JlcJDDgf8QXcGqDSOpi6dDqQwfzHVpv9eogE5upWXqb5HXPPoe_W5.speex


	request:
	POST /uploadWav?packageId=1&amp; wavUrl=https://gmh5api.guiji.ai/upload/jssdk/282/3GLik2N4gT7JlcJDDgf8QXcGqDSOpi6dDqQwfzHVpv9eogE5upWXqb5HXPPoe_W5.speex&amp; text=hello HTTP/1.1
	Host: localhost:28080


	response:
	{
		"code": "0000",
		"msg": "success",
		"data": null
	}


# web管理页面
1.语料训练列表查询
http://{ip}:{port}/packages/{页数}/{每页条数}
例如:
http://192.168.1.121:14433/packages/1/10

response:

{
    "code": "0000",
    "msg": "success",
    "data": {
        "content": [
            {
                "id": 13,
                "name": "tom1566468668778",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:11:09.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 12,
                "name": "tom1566468549128",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:09:09.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 11,
                "name": "tom1566468544473",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:09:04.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 10,
                "name": "暮云清寒1566468539751",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:09:00.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 9,
                "name": "tom1566468533544",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:08:54.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 8,
                "name": "tom1566468515541",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:08:36.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 7,
                "name": "暮云清寒1566468510484",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:08:30.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 6,
                "name": "暮云清寒1566468487710",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:08:08.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 5,
                "name": "VIP用户1566468439208",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:07:19.000+0000",
                "url": "",
                "total": 0
            },
            {
                "id": 4,
                "name": "VIP用户1566468326473",
                "status": 0,
                "trainingDoneTime": null,
                "createBy": "admin",
                "createTime": "2019-08-22T10:05:26.000+0000",
                "url": "",
                "total": 0
            }
        ],
        "pageable": {
            "sort": {
                "sorted": true,
                "unsorted": false,
                "empty": false
            },
            "pageSize": 10,
            "pageNumber": 1,
            "offset": 10,
            "paged": true,
            "unpaged": false
        },
        "totalElements": 23,
        "totalPages": 3,
        "last": false,
        "first": false,
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "numberOfElements": 10,
        "size": 10,
        "number": 1,
        "empty": false
    }
}

2.语料详情列表查询
http://{ip}:{port}/files/{packageId}/{页数}/{每页条数}
例如:
http://192.168.1.121:14433/files/2/1/10

response:
{
    "code": "0000",
    "msg": "success",
    "data": {
        "content": [
            {
                "id": 1,
                "name": "11",
                "text": "Helloween",
                "url": "/test",
                "packageId": 2,
                "createTime": "2019-08-23T09:14:38.000+0000"
            },
            {
                "id": 2,
                "name": "11",
                "text": "Helloween",
                "url": "/test",
                "packageId": 2,
                "createTime": "2019-08-23T09:14:38.000+0000"
            },
            {
                "id": 3,
                "name": "11",
                "text": "Helloween",
                "url": "/test",
                "packageId": 2,
                "createTime": "2019-08-23T09:14:38.000+0000"
            }
        ],
        "pageable": {
            "sort": {
                "sorted": true,
                "unsorted": false,
                "empty": false
            },
            "pageSize": 10,
            "pageNumber": 0,
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 3,
        "first": true,
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "numberOfElements": 3,
        "size": 10,
        "number": 0,
        "empty": false
    }
}

3.删除语料包 POST
http://{ip}:{port}/delete/package/{id}
http://192.168.1.121:14433/delete/file/32

{
    "code": "0",
    "msg": "success",
    "data": null
}

4.删除语料 POST
http://{ip}:{port}/delete/file/{id}
http://192.168.1.121:14433/delete/package/32

{
    "code": "0",
    "msg": "success",
    "data": null
}



# dev 环境
用户:yykl  密码: 123456
scp target/training-0.0.1-SNAPSHOT.jar yykl@114.55.243.219:/home/yykl/apps

web 首页: 
https://xiehu-dev.guiji.ai

后台接口样例
https://xiehu-yykl.guiji.ai/cloneApi/packageId
nohup java -jar -Dspring.profiles.active=test trainning-0.0.1-SNAPSHOT.jar &



# speex 转 wav
yum install speex-devel
chmod +x spx2wav
./spx2wav 3GLik2N4gT7JlcJDDgf8QXcGqDSOpi6dDqQwfzHVpv9eogE5upWXqb5HXPPoe_W5.speex 1.wav


# 模型训练和启动
yum -y install expect
/home/clone/bin/cpZip.sh



