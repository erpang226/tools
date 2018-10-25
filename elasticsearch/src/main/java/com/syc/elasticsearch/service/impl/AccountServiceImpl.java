package com.syc.elasticsearch.service.impl;

import com.syc.elasticsearch.model.Account;
import com.syc.elasticsearch.repository.AccountRepository;
import com.syc.elasticsearch.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Account> findByName(String name, Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public List<Account> findByTitle(String title) {
        return null;
    }
}
