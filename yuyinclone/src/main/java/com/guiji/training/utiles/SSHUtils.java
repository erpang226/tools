package com.guiji.training.utiles;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class SSHUtils {
    //字符编码默认是utf-8
    private static String DEFAULTCHART = "UTF-8";
    private Connection conn;
    private String ip;
    private String userName;
    private String userPwd;
    private int port;

    public SSHUtils(String ip, String userName, String userPwd,int port) {
        this.ip = ip;
        this.userName = userName;
        this.userPwd = userPwd;
        this.port = port;
    }


    public SSHUtils() {

    }

    /**
     * 远程登录linux的主机
     *
     * @return 登录成功返回true，否则返回false
     * @author Ickes
     * @since V0.1
     */
    public Boolean login() {
        boolean flg = false;
        try {
            conn = new Connection(ip,port);
            conn.connect();//连接
            flg = conn.authenticateWithPassword(userName, userPwd);//认证
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flg;
    }

    /**
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     * @author Ickes
     * 远程执行shll脚本或者命令
     * @since V0.1
     */
    public String execute(String cmd) {
        String result = "";
        try {
            if (login()) {
                Session session = conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                // session.startShell();


                result = processStdout(session.getStdout(), DEFAULTCHART);


                //如果为得到标准输出为空，说明脚本执行出错了
                if (StringUtils.isBlank(result)) {
                    result = processStdout(session.getStderr(), DEFAULTCHART);
                }
                System.out.println("execute(1) 返回结果:");
                System.out.println(result);
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     * @author Ickes
     * 远程执行shll脚本或者命令
     * @since V0.1
     */
    public String execute1(String cmd) {
        String result = "";
        try {
            if (login()) {
                Session session = conn.openSession();//打开一个会话
                session.requestPTY("bash");
                // session.execCommand(cmd);//执行命令
                session.startShell();

                PrintWriter out = new PrintWriter(session.getStdin());

                // 输入待执行命令

                out.println(cmd);

                out.println("exit");
                out.close();

                result = processStdout(session.getStdout(), DEFAULTCHART);


                //如果为得到标准输出为空，说明脚本执行出错了
                if (StringUtils.isBlank(result)) {
                    System.out.println("执行失败");
                    result = processStdout(session.getStderr(), DEFAULTCHART);
                }
                System.out.println("execute1() 返回结果:");

                System.out.println(result);
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * @param cmd 即将执行的命令
     * @return 命令执行成功后返回的结果值，如果命令执行失败，返回空字符串，不是null
     * @author Ickes
     * 远程执行shll脚本或者命令
     * @since V0.1
     */
    public String executeSuccess(String cmd) {
        String result = "";
        try {
            if (login()) {
                Session session = conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result = processStdout(session.getStdout(), DEFAULTCHART);
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析脚本执行返回的结果集
     *
     * @param in      输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     * @author Ickes
     * @since V0.1
     */
    private String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        ;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void setCharset(String charset) {
        DEFAULTCHART = charset;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SSHUtils rec = new SSHUtils("192.168.1.72", "clone", "clone123clone",22);
        //执行命令
        String str = rec.execute("expect /home/clone/bin/synthesis.sh");

        System.out.println("=========");
        System.out.println(str);
//        //System.out.println(rec.execute("/usr/ksybak/myshell/tomcat-fw.sh"));
//
//
//        SSHUtils tool = new SSHUtils("192.168.1.72", "clone", "clone123clone", "utf-8");
//
//        //        String result = tool.exec("./test.sh xiaojun");
//        String result = tool.exec("cd /zhd;expect cp.sh");
//        System.out.println(result);

    }

}
