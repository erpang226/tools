package com.syc.elasticsearch.service;

import com.syc.elasticsearch.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface AccountService {

    Page<Account> findByName(String name, Pageable pageable);


    List<Account> findByTitle(String title);

}
