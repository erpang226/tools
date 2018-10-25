package com.syc.elasticsearch.service.impl;

import com.syc.elasticsearch.ElasticsearchApplication;
import com.syc.elasticsearch.model.Account;
import com.syc.elasticsearch.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void findByTitle() {

        Iterable<Account> list = accountRepository.findAll();

        for (Account account : list) {
            System.out.println(account.getUser());
        }

    }
}