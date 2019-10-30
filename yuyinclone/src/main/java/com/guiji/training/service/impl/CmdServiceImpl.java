package com.guiji.training.service.impl;

import com.guiji.training.service.CmdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/23
 * author: song yong chang
 */
@Service
@Slf4j
public class CmdServiceImpl implements CmdService {

    @Override
    public void execCommand(String cmd) {
        log.info("exec [{}]", cmd);
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd, null, null);
            try (InputStream stderr = proc.getInputStream();
                 InputStreamReader isr = new InputStreamReader(stderr, "GBK");
                 BufferedReader br = new BufferedReader(isr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    log.info("cmd [{}]", line);
                }
            }
        } catch (Exception e) {
            log.error("exec [{}] failed", cmd, e);
        }
    }
}
