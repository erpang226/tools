package com.syc.elasticsearch.controller;

import com.syc.elasticsearch.model.Account;
import com.syc.elasticsearch.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountController")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/select")
    public List<Account> select(@RequestParam("pageNum") Integer pageNum, @RequestParam("query") String query) {

        return accountService.select(pageNum, 10, query);
    }

    @PostMapping("/save")
    public String save(@RequestBody Account account) {
        accountService.save(account);
        return "success";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody Account account) {
        accountService.delete(account);
        return "success";
    }

}
