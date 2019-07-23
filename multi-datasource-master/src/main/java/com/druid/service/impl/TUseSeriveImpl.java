package com.druid.service.impl;

import com.druid.annotation.Master;
import com.druid.annotation.Slave;
import com.druid.dao.User233Mapper;
import com.druid.entity.User233;
import com.druid.service.TUseSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description baipao
 * @encoding UTF-8
 * @date 2019-04-08
 * @time 18:03
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Service
public class TUseSeriveImpl implements TUseSerive {


    @Autowired
    private User233Mapper user233Mapper;



    @Master
    @Override
    public List<User233> listMaster() {
        return user233Mapper.selectAll();
    }

    @Slave
    @Override
    public List<User233> listSlave() {
        return user233Mapper.selectAll();
    }




}
