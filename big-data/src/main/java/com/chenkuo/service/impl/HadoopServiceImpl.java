package com.chenkuo.service.impl;

import com.chenkuo.service.HadoopService;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;


@Service
public class HadoopServiceImpl implements HadoopService {

    Logger logger = LoggerFactory.getLogger(HadoopServiceImpl.class);

    @Override
    public void readAnaWrite() throws IOException {

        String uri = "hdfs://172.17.0.3:9000";
        Path inputPath = new Path("/user/root/input");
        Path outputPath = new Path("/user/root/output/syc.txt");
        Configuration configuration = new Configuration();
        configuration.set("dfs.permissions.enabled","false");
        FileSystem inputfs = FileSystem.get(URI.create(uri), configuration);
        FileSystem outputfs = FileSystem.get(URI.create(uri), configuration);

        FileStatus[] fileStatus = inputfs.listStatus(inputPath);

        for (FileStatus status : fileStatus) {
            logger.info("status: {}",status.getPath());
            List<String> lines;
            try(FSDataInputStream fsdis = inputfs.open(status.getPath())){
                 lines= IOUtils.readLines(fsdis, "utf-8");
            }
            lines.forEach(l->logger.info(l));
            try (FSDataOutputStream fsdos = outputfs.create(outputPath)) {
                IOUtils.writeLines(lines,"utf-8",fsdos.getWrappedStream(),"utf-8");
            }
        }



    }

}
