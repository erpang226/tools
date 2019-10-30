package com.guiji.training.service.impl;

import com.guiji.training.service.CmdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CmdServiceImplTest {

    @Autowired
    private CmdService cmdService;


    @Test
    public void execCommand() {
        cmdService.execCommand("/home/syc/桌面/tool/spx2wav " +
                "/home/syc/桌面/tool/3GLik2N4gT7JlcJDDgf8QXcGqDSOpi6dDqQwfzHVpv9eogE5upWXqb5HXPPoe_W5.speex " +
                "/home/syc/桌面/tool/2.wav");
    }
}