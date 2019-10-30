package com.guiji.training.controller;

import com.guiji.training.utiles.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeqController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeqController.class);
    @GetMapping(value = "/getSeqId")
    public String getChildrenFile() {
        LOGGER.info("生成seqId");
       return UUIDUtil.get();
    }
}
