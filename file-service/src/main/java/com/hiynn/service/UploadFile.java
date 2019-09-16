package com.hiynn.service;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class UploadFile implements Serializable {

    private static final long serialVersionUID = -6037723834114220014L;

    private String fileName;

    private byte[] file;

    public UploadFile(String fileName, byte[] file) {
        this.fileName = fileName;
        this.file = file;
    }
}
