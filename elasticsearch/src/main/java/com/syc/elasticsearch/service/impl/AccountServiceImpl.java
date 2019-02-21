package com.syc.elasticsearch.service.impl;

import com.syc.elasticsearch.model.Account;
import com.syc.elasticsearch.repository.AccountRepository;
import com.syc.elasticsearch.service.AccountService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> select(int pageNum, int pageSize, String query) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(QueryBuilders.matchPhraseQuery("name", query))
                .withQuery(QueryBuilders.matchPhraseQuery("desc", query))
                .build();

        Page<Account> page = accountRepository.search(searchQuery);
        return page.getContent();
    }



}
