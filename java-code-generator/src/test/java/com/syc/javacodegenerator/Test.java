package com.syc.javacodegenerator;

import com.greedystar.generator.invoker.SingleInvoker;
import com.greedystar.generator.invoker.base.Invoker;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description:
 * CreateTime: 19-8-2 上午10:57
 *
 * @author syc
 * @version 1.0
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {


    @org.junit.Test
    public void test(){

        Invoker invoker = new SingleInvoker.Builder()
                .setTableName("blade_role")
//                .setClassName("User") // 可空. 若空则自动根据表名生成类名，如: user->User，sys_role->SysRole
                .build();
        invoker.execute();


    }


}
