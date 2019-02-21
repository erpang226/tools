package com.syc.elasticsearch.repository;

import com.syc.elasticsearch.model.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ElasticsearchRepository<Account, String> {


}
