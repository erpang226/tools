package com.guiji.training.utiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/28
 * author: song yong chang
 */
public class FileUtil {


    public static boolean deleteDirectory(File file) throws IOException {
        if (file.isFile()) {
            Files.deleteIfExists(file.toPath());
        } else {
            File[] list = file.listFiles();
            if (list != null) {
                for (File f : list) {
                    deleteDirectory(f);
                }
                Files.deleteIfExists(file.toPath());
            }
        }
        return true;
    }
}
