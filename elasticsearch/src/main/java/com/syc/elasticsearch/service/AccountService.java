package com.syc.elasticsearch.service;

import com.syc.elasticsearch.model.Account;

import java.util.List;


public interface AccountService {


    Account save(Account account);

    void delete(Account account);

    Account update(Account account);

    List<Account> select(int pageNum, int pageSize, String query);


}
