package com.guiji.training.utiles;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.StreamGobbler;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

public class Ftp {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ftp.class);
//    private  final static String ip="192.168.1.72";
//    private  final static String username="clone";
//    private  final static String password="clone123clone";

    public String uploadZip(String fileName, String sexSuffex) throws Exception {
        File file = new File("D:\\ducument\\" + fileName);
        Ftp ft = new Ftp();
        Session s = ft.getSession("", 22, "", "");//103.27.109.200
        Channel channel = ft.getChannel(s);
        ChannelSftp sftp = (ChannelSftp) channel;
        String upload = ft.uploadFile(sftp, "/home/clone/datasets", file);
        String finalDicName = fileName + sexSuffex;
        //ft.remoteZipToFile(fileName,sexSuffex);


        sftp.rename("/home/clone/datasets/test", "/home/clone/datasets/test" + sexSuffex);
        System.out.println(upload);
        ft.closeAll(sftp, channel, s); //关闭连接
        return "/home/clone/datasets/test" + sexSuffex;
    }

    public Vector download(String filePath, String ip, String username, String password) throws Exception {


        System.out.println("-----" + filePath + "==========");
        Ftp ft = new Ftp();
        //TODO
        Session s = ft.getSession(ip, 22, username, password);
        Channel channel = ft.getChannel(s);
        ChannelSftp sftp = (ChannelSftp) channel;
        Vector vector = null;
        try {
            vector = sftp.ls(filePath + "/wavs/*.wav");///home/root/datasets/test_*
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File uploadVedio = new File(path.getAbsolutePath(), "static/upload/video/");
        System.out.println("uploadVedio" + uploadVedio);
        if (!uploadVedio.exists()) {
            uploadVedio.mkdirs();
        }
        for (Object obj : vector) {
            if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
                String fileName1 = ((com.jcraft.jsch.ChannelSftp.LsEntry) obj).getFilename();
                ft.download(filePath + "/wavs/", fileName1, uploadVedio.toString(), sftp);
            }
        }

        // ft.download(filePath, "wavs.txt", "D:\\硅基\\文档\\test\\", sftp);
        return vector;
    }

    public Channel getChannel(Session session) {
        Channel channel = null;
        try {
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("get Channel success!");
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return channel;
    }

    public Session getSession(String host, int port, String username,
                              final String password) {
        Session session = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect();
        } catch (JSchException e) {
        }
        return session;
    }

    /**
     * 创建文件夹
     *
     * @param sftp
     * @param dir  文件夹名称
     */
    public void mkdir(ChannelSftp sftp, String dir) {
        try {
            sftp.mkdir(dir);
            System.out.println("创建文件夹成功！");
        } catch (SftpException e) {
            System.out.println("创建文件夹失败！");
        }
    }

    /**
     * @param sftp
     * @param dir  上传目录
     * @param file 上传文件
     * @return
     */
    public String uploadFile(ChannelSftp sftp, String dir, File file) {
        String result = "";
        try {
            sftp.cd(dir);

            // File file = new File("D://34.txt"); //要上传的本地文件
            if (file != null) {
                sftp.put(new FileInputStream(file), file.getName());
                result = "上传成功！";
            } else {
                result = "文件为空！不能上传！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "上传失败！";
        }
        return result;
    }

    public String rename(String fileName, String sexSuffex, String ip, String username, String password,int port) throws Exception {
        LOGGER.info("fileName:[{}],sexSuffex:[{}]", fileName, sexSuffex);
        Ftp ft = new Ftp();
        Session s = ft.getSession(ip, port, username, password);
        Channel channel = ft.getChannel(s);
        ChannelSftp sftp = (ChannelSftp) channel;
        String name = fileName.substring(0, fileName.indexOf("."));
        String a = name + sexSuffex;
        sftp.rename("/home/clone/datasets/" + name, "/home/clone/datasets/" + a);
        return "/home/clone/datasets/" + a;
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @param sftp
     */
    public String download(String directory, String downloadFile,
                           String saveFile, ChannelSftp sftp) {
        String result = "";
        try {
            sftp.cd(directory);
            if (downloadFile == null) {
                sftp.get(saveFile);
//                sftp.
            } else {
                sftp.get(downloadFile, saveFile);
            }


            result = "下载成功！";
        } catch (Exception e) {
            result = "下载失败！";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public String delete(String directory, String deleteFile, ChannelSftp sftp) {
        String result = "";
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
            result = "删除成功！";
        } catch (Exception e) {
            result = "删除失败！";
            e.printStackTrace();
        }
        return result;
    }

    private void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    public void closeAll(ChannelSftp sftp, Channel channel, Session session) {
        try {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Boolean remoteZipToFile(String fileName, String suffix, String ip, String username, String password,int port) throws Exception {
        LOGGER.info("remoteZipToFile fileName:[{}]",fileName);
        LOGGER.info("remoteZipToFile suffix:[{}]",suffix);
        String path = "/home/clone/datasets";
        Boolean result = false;
        try {
            Connection connection = new Connection(ip,port);// 创建一个连接实例
//            connection.connect();// Now connect
//            boolean isAuthenticated = connection.authenticateWithPassword("root",
//                    "39ad92f640d5");// Authenticate
//            if (isAuthenticated == false)
//                throw new IOException("user and password error");
//            ch.ethz.ssh2.Session sess = connection.openSession();// Create a session
//            System.out.println("start exec command.......");
//            sess.requestPTY("bash");
//            sess.startShell();
            ch.ethz.ssh2.Session sess = getSess(connection, username, password);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            InputStream stderr = new StreamGobbler(sess.getStderr());
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));
            PrintWriter out = new PrintWriter(sess.getStdin());
            System.out.println("------=" + fileName + ":" + suffix + "==-----");
            String name = fileName.substring(0, fileName.indexOf("."));
            String tempName = name + suffix;
            String rmCmd = "rm -rf " + tempName;
            out.println("cd " + path + "/");
            out.println(rmCmd);
            out.println("ll");
            out.println("unzip -o " + fileName+" -d "+ name);//解压zip格式
            // out.println("unzip -o " + fileName + "  -d /" + decpath + "/");//解压zip格式
            //out.println("tar zxvf " + fileName + "  -C /" + decpath + "/");//解压tar格式
            out.println("ll");

            out.println("exit");
            out.close();
            sess.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 30000);
            System.out.println("下面是从stdout输出:");
            while (true) {
                String line = stdoutReader.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
            System.out.println("下面是从stderr输出:");
            while (true) {
                String line = stderrReader.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close();/* Close this session */
            connection.close();/* Close the connection */
            result = true;
        } catch (IOException e) {
            e.printStackTrace(System.err);
            result = false;
            System.exit(2);
        }
        return result;

    }

//    public static void copyPbTO(String fileName)throws Exception{
//        Connection connection = new Connection("103.27.109.200");// 创建一个连接实例
//        ch.ethz.ssh2.Session sess = getSess(connection);
//        InputStream stdout = new StreamGobbler(sess.getStdout());
//        InputStream stderr = new StreamGobbler(sess.getStderr());
//        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
//        BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));
//        PrintWriter out = new PrintWriter(sess.getStdin());
//        out.println("scp -P 50001 root@222.190.139.246:/root/gjtts_server/model/maohui.pb /home/root/datasets/test_M");
//        out.println(1);
//        out.println("exit");
//        out.close();
//        sess.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 30000);
//        System.out.println("下面是从stdout输出:");
//        while (true) {
//            String line = stdoutReader.readLine();
//            if (line == null)
//                break;
//            System.out.println(line);
//        }
//        System.out.println("下面是从stderr输出:");
//        while (true) {
//            String line = stderrReader.readLine();
//            if (line == null)
//                break;
//            System.out.println(line);
//        }
//        System.out.println("ExitCode: " + sess.getExitStatus());
//        sess.close();/* Close this session */
//        connection.close();/* Close the connection */
//    }

    public static ch.ethz.ssh2.Session getSess(Connection connection, String username, String password) throws Exception {

        connection.connect();//
        boolean isAuthenticated = connection.authenticateWithPassword(username,
                password);// Authenticate
        if (isAuthenticated == false)
            throw new IOException("user and password error");
        ch.ethz.ssh2.Session sess = connection.openSession();// Create a session
        System.out.println("start exec command.......");
        sess.requestPTY("bash");
        sess.startShell();
        return sess;
    }

    public void uploadTXT(String filePath, String fileName) throws Exception {
        File file = new File("D:\\硅基\\文档\\test\\" + fileName);
        Ftp ft = new Ftp();
        Session s = ft.getSession("103.27.109.200", 22, "root", "39ad92f640d5");
        Channel channel = ft.getChannel(s);
        ChannelSftp sftp = (ChannelSftp) channel;
        String upload = ft.uploadFile(sftp, filePath, file);
        // ft.remoteZipToFile(fileName );
        System.out.println(upload);
        ft.closeAll(sftp, channel, s); //关闭连接
    }

    public static File renameFile(File file, String name) {
        String fileName = file.getParent() + File.separator + name;
        File dest = new File(fileName);
        file.renameTo(dest);
        return dest;
    }

    public static void main(String[] args) throws Exception {
        String fileName = "test.zip";
        System.out.println(fileName.substring(0, fileName.indexOf(".")));
//        File file = new File("D:\\tt\\test.zip");
//        renameFile(file,"zhd.zip");
//        copyPbTO("");

//        Connection connection = new Connection("103.27.109.200");// 创建一个连接实例
//        connection.connect();// Now connect
//        boolean isAuthenticated = connection.authenticateWithPassword("root",
//                "39ad92f640d5");
//
//        if (isAuthenticated == false) {
//            System.out.println("Authentication failed");
//            throw new IOException("Authentication failed");
//        }
//        ch.ethz.ssh2.Session sess = connection.openSession();// Create a session
//        System.out.println("start exec command.......");
//        sess.requestPTY("bash");
//        sess.startShell();
//        PrintWriter out = new PrintWriter(sess.getStdin());
//        out.println("ssh -p 50001 root@222.190.139.246");
//        out.println("1");
    }
}
