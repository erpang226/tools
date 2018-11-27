package com.chenkuo.service.impl;

import com.chenkuo.service.HadoopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HadoopServiceImplTest {

    @Autowired
    private HadoopService hadoopService;


    @Test
    public void readAnaWrite() {
        try {
            hadoopService.readAnaWrite();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}