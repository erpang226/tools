package com.syc.elasticsearch.service.impl;

import com.syc.elasticsearch.ElasticsearchApplication;
import com.syc.elasticsearch.model.Account;
import com.syc.elasticsearch.repository.AccountRepository;
import com.syc.elasticsearch.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void save() {
        Account account = new Account();
        account.setId("004");
        account.setName("tom");
        account.setTitle("eng");
        account.setDesc("java python c#");
        accountService.save(account);
    }

    @Test
    public void delete() {
        /*删除的时候通过id来定位
         */
        Account account = new Account();
        account.setId("001");
        account.setName("tom");
        account.setTitle("eng");
        account.setDesc("java python c#");
        accountService.save(account);

        accountService.delete(account);
    }

    @Test
    public void update() {
        /*
            更新操作也是通过id来定位,并且返回最新版本的数据
            指令操作：   GET PUT DELETE POST
            <HTTP Verb> /<Index>/<Type>/<ID>
         */
        Optional<Account> old = accountRepository.findById("003");
        Account account = old.orElseGet(() -> {
            Account a = new Account();
            a.setId("003");
            return a;
        });
        account.setName("Tom Taylor");
        account.setTitle("engineer");
        account.setDesc("java python C# go");
        accountService.save(account);
    }

    @Test
    public void select() {



    }
}