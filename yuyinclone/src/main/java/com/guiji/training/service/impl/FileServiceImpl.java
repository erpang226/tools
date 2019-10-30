package com.guiji.training.service.impl;

import com.guiji.training.common.BizException;
import com.guiji.training.common.Result;
import com.guiji.training.common.ReturnEnum;
import com.guiji.training.entity.Package;
import com.guiji.training.repositry.FileRepository;
import com.guiji.training.repositry.PackageRepository;
import com.guiji.training.service.CmdService;
import com.guiji.training.service.FileService;
import com.guiji.training.utiles.FileUtil;
import com.guiji.training.utiles.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.zeroturnaround.zip.ZipUtil;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file-root-path}")
    private String rootPath;

    @Value("${zip-root-path}")
    private String zipRootPath;

    @Value("${speex-to-wav}")
    private String speexToWavTool;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CmdService cmdService;


    @Override
    @Transactional
    public Result.Data<String> uploadFile(String name, InputStream inputStream) {

        if (!name.endsWith(".zip")) {
            throw new BizException(ReturnEnum.ERROR_0003);
        }
        String uuid = UUIDUtil.get();
        String localPath = rootPath + File.separator + uuid;
        File file = new File(localPath);
        if (!file.exists()) {
            if (file.mkdir()) {
                logger.info("创建文件夹【{}】成功", file.getPath());
            } else {
                logger.error("创建文件夹【{}】失败", file.getPath());
            }
        }
        File zipFile = new File(localPath + File.separator + name);
        saveFile(name, inputStream, zipFile);
        // unzip
        File[] childrenFiles = unzip(zipFile);
        // save db meta data
        Package p = new Package(null, "", 0, null, "admin", new Date(),
                File.separator + uuid + File.separator + name, childrenFiles.length);
        Package pr = packageRepository.save(p);
        saveFileMetaData(name, localPath, childrenFiles, pr);

        return Result.getSuccessResult(zipFile.getAbsolutePath());
    }

    @Override
    public Result.Data uploadFile(Long packageId, String fileName, byte[] bytes, String text) throws IOException {
        Optional<Package> optional = packageRepository.findById(packageId);
        Package p = optional.orElseThrow(() -> new BizException(ReturnEnum.ERROR_0005));
        //避免重复提交
        if (p.getStatus() >= 4 && p.getStatus() <= 8) {
            throw new BizException(ReturnEnum.ERROR_0006);
        }
        // save .speex file and translate .wav
        saveSpeex(fileName, bytes, p, text);
        // 检查批次文件是否上传完成
        com.guiji.training.entity.File f = new com.guiji.training.entity.File();
        f.setPackageId(packageId);

        long count = fileRepository.count(Example.of(f));
        if (count >= p.getTotal()) {
            p.setStatus(8);
            packageRepository.save(p);
            //生成zip包
            zipFiles(packageId);
        }
        return null;
    }

    private void saveSpeex(String fileName, byte[] bytes, Package p, String text) throws IOException {
        String parentDir = rootPath + p.getUrl() + File.separator + "speex";
        createNewDir(parentDir);

        String localName = parentDir + File.separator + fileName;
        File f = new File(localName);
        saveFile(bytes, f);
        // speex -> wav
        String wavs = p.getUrl() + File.separator + "wavs";
        File wavsFile = createNewDir(rootPath + wavs);
        String nextWavNum = getNext(wavsFile);

        String wavFile = wavs + File.separator + nextWavNum + ".wav";
        cmdService.execCommand(speexToWavTool + " " + f.getAbsolutePath() + " " + rootPath + wavFile);
        // record wavs.txt
        File textFile = createNewFile(rootPath + p.getUrl() + File.separator + "wavs.txt");
        try (FileWriter fw = new FileWriter(textFile, true)) {
            fw.write(nextWavNum + "|" + text + "\n");
        }
        com.guiji.training.entity.File s = new com.guiji.training.entity.File(null, nextWavNum + ".wav", text, wavFile, p.getId(), new Date());

        fileRepository.save(s);
    }

    private String getNext(File wavsFile) {
        if (null == wavsFile.listFiles()) {
            return "1";
        } else {
            return wavsFile.listFiles().length + 1 + "";
        }
    }

    private File createNewDir(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) {
                logger.info("创建文件[{}]", file.getAbsolutePath());
            } else {
                logger.info("未创建文件[{}]", file.getAbsolutePath());
            }
        }
        return file;
    }

    private File createNewFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            if (file.createNewFile()) {
                logger.info("创建文件[{}]", file.getAbsolutePath());
            } else {
                logger.info("未创建文件[{}]", file.getAbsolutePath());
            }
        }
        return file;
    }

    @Override
    public Result.Data<String> zipFiles(Long packageId) throws IOException {
        Optional<Package> optional = packageRepository.findById(packageId);
        Package p = optional.orElseThrow(() -> new BizException("没有找到该文件"));
        String path = rootPath + p.getUrl();
        File rootDir = new File(path);
        File speex = new File(rootDir, "speex");
        if (FileUtil.deleteDirectory(speex)) {
            logger.info("delete {}/speex", path);
        }
        File zip = new File(zipRootPath + File.separator + packageId + ".zip");
        ZipUtil.pack(rootDir, zip);
        return Result.getSuccessResult(zip.getAbsolutePath());
    }

    private void saveFileMetaData(String name, String localPath, File[] childrenFiles, Package pr) {
        // get text
        Map<String, String> lines;
        try {
            lines = getText(name, localPath);
        } catch (IOException e) {
            logger.error("文本文件内容错误");
            throw new BizException(ReturnEnum.ERROR_0004, e);
        }

        List<com.guiji.training.entity.File> items = new ArrayList<>();
        for (File childrenFile : childrenFiles) {
            if (childrenFile.getName().endsWith(".wav")) {
                String textFileName = childrenFile.getName();
                String content = lines.get(textFileName.split(".wav")[0]);
                com.guiji.training.entity.File f = new com.guiji.training.entity.File(null, textFileName, content,
                        childrenFile.getAbsolutePath(), pr.getId());
                items.add(f);
            }
        }
        fileRepository.saveAll(items);
    }

    private Map<String, String> getText(String name, String localPath) throws IOException {
        File textFile = new File(localPath + File.separator + name.substring(0, name.length() - 4) + File.separator + "wavs.txt");
        FileReader fr = new FileReader(textFile);
        Map<String, String> lines = new HashMap<>();

        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("\\|");
                lines.put(strings[0], strings[1]);
            }
        } catch (Exception e) {
            throw new BizException(ReturnEnum.ERROR_0004, e);
        }
        return lines;
    }

    private void saveFile(String name, InputStream inputStream, File zipFile) {
        try (OutputStream output = new FileOutputStream(zipFile)) {
            //保存文件到指定路径
            IOUtils.copy(inputStream, output);
        } catch (Exception e) {
            logger.error("文件上传失败{}", name, e);
            throw new BizException(ReturnEnum.ERROR_0001);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                logger.error("文件上传失败，网络异常", e);
            }
        }
    }

    /**
     * @param bytes speex file bytes
     * @param file  speex file
     */
    private void saveFile(byte[] bytes, File file) {
        try (OutputStream output = new FileOutputStream(file)) {
            //保存文件到指定路径
            IOUtils.write(bytes, output);
        } catch (Exception e) {
            logger.error("保存文件到指定路径失败", e);
            throw new BizException(ReturnEnum.ERROR_0001);
        }
    }

    private File[] unzip(File file) {
        String dir = file.getAbsolutePath().split(".zip")[0];
        File des = new File(dir);
        ZipUtil.unpack(file, des);
        File wavs = new File(dir + File.separator + "wavs");
        return wavs.listFiles();
    }

    @Override
    public void deleteFile(Long id) throws IOException {
        Optional<com.guiji.training.entity.File> optionl = fileRepository.findById(id);
        com.guiji.training.entity.File file = optionl.orElseThrow(() -> new BizException(ReturnEnum.ERROR_0005));
        File f = new File(rootPath + File.separator + file.getUrl());
        Files.deleteIfExists(f.toPath());
        fileRepository.deleteById(id);
        // 删除后更改状态
        Optional<Package> optionlP = packageRepository.findById(file.getPackageId());
        Package p = optionlP.get();
        p.setStatus(0);
        packageRepository.save(p);
    }


    @Override
    public File downloadFile(String fileId) throws BizException {
        return null;
    }


}
