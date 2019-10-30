package com.guiji.training.service;

import com.guiji.training.entity.Package;

import java.io.IOException;

public interface PackageService {

    Package saveNewPackage(String name, String userId, int total) throws IOException;


    void deletePackageAndFiles(Long id) throws IOException;
}

