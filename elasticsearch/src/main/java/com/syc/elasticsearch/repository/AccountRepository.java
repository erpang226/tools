package com.syc.elasticsearch.repository;

import com.syc.elasticsearch.model.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface AccountRepository extends ElasticsearchRepository<Account, String> {


}
