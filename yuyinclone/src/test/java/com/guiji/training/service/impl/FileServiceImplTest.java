package com.guiji.training.service.impl;

import com.guiji.training.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileServiceImplTest {

    @Autowired
    private FileService fileService;

    @Test
    public void uploadFile() {
    }

    @Test
    public void uploadFile1() {
    }

    @Test
    public void zipFiles() throws IOException {
        fileService.zipFiles(2L);
    }

    @Test
    public void deleteFile() {
    }

    @Test
    public void downloadFile() {
    }
}