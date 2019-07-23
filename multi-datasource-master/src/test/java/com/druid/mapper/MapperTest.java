package com.druid.mapper;

import com.druid.dao.User233Mapper;
import com.druid.entity.User233;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Description:
 * CreateTime: 19-7-23 上午9:36
 *
 * @author syc
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private User233Mapper user233Mapper;

    @Test
    public void test(){
        List<User233> list = user233Mapper.selectAll();

        list.forEach(System.out::println);

    }

}
