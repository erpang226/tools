package com.syc.javacodegenerator;

import com.greedystar.generator.invoker.SingleInvoker;
import com.greedystar.generator.invoker.base.Invoker;

/**
 * Description:
 * CreateTime: 19-8-2 下午2:28
 *
 * @author syc
 * @version 1.0
 */
public class Generator {

    public static void main(String[] args) {

        Invoker invoker = new SingleInvoker.Builder()
                .setTableName("blade_role")
                .build();
        invoker.execute();

    }
}
