package com.guiji.training.controller;

import com.alibaba.fastjson.JSON;
import com.guiji.training.entity.Model;
import com.guiji.training.entity.ResultBack;
import com.guiji.training.entity.SeqInfo;
import com.guiji.training.mapper.TrainAndStartMapper;
import com.guiji.training.result.CommonResp;
import com.guiji.training.service.FileService;
import com.guiji.training.utiles.Ftp;
import com.guiji.training.utiles.SSHUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class TrainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainController.class);
    @Value("${clone.train.ip}")
    private String ip;

    @Value("${clone.train.username}")
    private String username;

    @Value("${clone.train.password}")
    private String password;
    @Value("${clone.train.port}")
    private Integer port;

    @Value("${zip-root-path}")
    private String localZipPath;
    @Autowired
    private TrainAndStartMapper trainAndStartMapper;


    /**
     * 启动训练
     *
     * @param seqId
     * @param param 男 ：_M 女： _F
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/train", method = RequestMethod.GET)
    public CommonResp train(@RequestParam String seqId, @RequestParam String param) throws IOException, Exception {
        Model seqInfo = trainAndStartMapper.querySeq(seqId);
        CommonResp commonResp = new CommonResp();
        LOGGER.info("训练参数：" + seqId);

        if (StringUtils.isBlank(param) || StringUtils.isBlank(seqId)) {
            throw new Exception("参数不能为空");
        }

        if (seqInfo == null) {
            commonResp.setCode("-1");
            commonResp.setMsg("该批次不存在,无法启动训练");
            return commonResp;
        }

        if (seqInfo.getStatus() == 1) {
            commonResp.setMsg("训练中,不可重复训练");
            commonResp.setCode("0");
            return commonResp;
        }
        if (seqInfo.getStatus() == 2 || seqInfo.getStatus() == 5 || seqInfo.getStatus() == 4) {
            commonResp.setMsg("已训练完成");
            commonResp.setCode("0");
            return commonResp;
        }

        if (seqInfo.getStatus() == 8) {
            String fullLocalZipPath = localZipPath + File.separator + seqId + ".zip";
            LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>本地zip包路径为{}", fullLocalZipPath);
            String cmdZip = "expect /home/clone/bin/cpZip.sh  " + fullLocalZipPath;

            //将zip包搬迁到训练服务器上
            executeLinuxCmd(cmdZip);
            Ftp ft = new Ftp();
            //解压
            ft.remoteZipToFile(seqId + ".zip", param, ip, username, password, port);

            //重命名
            ft.rename(seqId + ".zip", param, ip, username, password, port);


            String trainParm = seqId + param;

            trainAndStartMapper.updateByKey(seqId, 1);

            String cmd = "bash train.sh " + trainParm + " 0" + " " + seqId.trim();

            System.out.println("-----java调用训练脚本开始----:" + cmd);
            excuteShell1(cmd, ip, username, password, port);
            System.out.println("----java调用训练脚本结束----");
            commonResp.setCode("0");
            commonResp.setMsg("正在训练中...");
            return commonResp;

        }
        commonResp.setMsg("请上传完,在训练");
        commonResp.setCode("-1");
        return commonResp;
    }

    /**
     * 启动
     *
     * @param seqId
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public CommonResp start(@RequestParam String seqId) throws IOException, Exception {

        LOGGER.info("启动接口,参数为:[{}]", seqId);
        Model seqInfo = trainAndStartMapper.querySeq(seqId);


        if (seqInfo == null) {
            throw new Exception("暂时没有该批次数据");
        }
        CommonResp commonResp = new CommonResp();
        if (StringUtils.isBlank(seqInfo.getStartName())) {
            commonResp.setMsg("请先执行训练");
            commonResp.setCode("-1");
            return commonResp;
        }
        if (seqInfo.getStatus() == 4) {
            commonResp.setMsg("正在启动中");
            commonResp.setCode("0");
            return commonResp;
        }
        if (seqInfo.getStatus() == 5) {
            commonResp.setMsg("已经启动");
            commonResp.setCode("0");
            return commonResp;
        }
        if (seqInfo.getStatus() == 1) {
            commonResp.setMsg("正在训练中");
            commonResp.setCode("0");
            return commonResp;
        }
        if (seqInfo.getStatus() != 2) {

            commonResp.setMsg("请先执行训练");
            commonResp.setCode("0");
            return commonResp;
        }
        trainAndStartMapper.updateStartByKey(seqId, 4, getNowTime(),null);

        int portS;
        if (seqInfo.getPort() == null) {
            portS = generatePort();
            if (portS > 26050) {
                commonResp.setCode("-1");
                commonResp.setMsg("端口号全部被占用了，无法启动");
                return commonResp;
            }
            trainAndStartMapper.updatePortByKey(seqId, portS);
        } else {
            portS = seqInfo.getPort();
        }
        LOGGER.info("-----启动开始-----");
        LOGGER.info("启动参数：" + seqInfo.getStartName());
        String cmd = "bash /home/clone/bin/synthesis.sh  " + portS + " " + seqInfo.getStartName();
        commonResp = excuteShell(cmd, ip, username, password, port);
        commonResp.setCode("1");
        LOGGER.info(">>>>>>>>>>>调用启动脚本结束");
        LOGGER.info(">>>>>>>>>>返回结果为:{}", commonResp.toString());
        RestTemplate restTemplate = new RestTemplate();
        Map map =new HashMap();
        map.put("model",seqInfo.getModelName());
        map.put("content","欢迎使用硅基智能语音机器人");
        LOGGER.info(">>>>>>>>>开始调用合成");
        HashMap hashMap =restTemplate.postForObject("http://172.16.251.47:38002/synPost",map, HashMap.class);
        LOGGER.info("hasmap:", JSON.toJSONString(hashMap));
//                    String code = hashMap.get("code").toString();
        String url = hashMap.get("data").toString();
        trainAndStartMapper.updateStartByKey(seqId, 5, getNowTime(),url);
        return commonResp;
    }


    /**
     * 训练结束 将pd文件copy到合成服务器
     *
     * @param content
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public CommonResp copyToOther(@RequestBody ResultBack content) throws IOException, Exception {
        System.out.println(content.getId());
        System.out.println(content.getSeqId());
        String name = content.getName();
        String seqId = content.getId();

        CommonResp commonResp = new CommonResp();
        if (content == null || StringUtils.isBlank(content.getName())) {
            commonResp.setCode("-1");
            commonResp.setMsg("name 参数为空！");
            System.out.println("name 参数为空");
            return commonResp;
        }

        try {
            //pb_model_path=$pb_model_dir/$user_v3.3.pb
            System.out.println("start:" + name);
            String file = name;
            int index = file.lastIndexOf("/") + 1;
            String temp = file.substring(index);
            System.out.println("temp=" + temp);
//            String modelName = temp.substring(0, temp.lastIndexOf("_"));
            String startParam = temp.substring(0, temp.indexOf("_") + 2);
            System.out.println("--------" + startParam + "--------");

            trainAndStartMapper.updateStartParmByKey(seqId, startParam, temp);
        } catch (Exception e) {
            e.printStackTrace();
            commonResp.setCode("-1");
            commonResp.setMsg("文件路径错误");
            commonResp.setObject(content.getName());
            return commonResp;
        }
        //将pd文件copy到合成服务器
        commonResp = excuteShell("expect /home/clone/bin/cp.sh " + name, ip, username, password, port);
        commonResp.setObject(name);
        Thread.sleep(2000);

        trainAndStartMapper.updateTrainByKey(seqId, 2, getNowTime());
        System.out.println(commonResp.toString());
        return commonResp;
    }


    private static CommonResp excuteShell(String targetPath, String ip, String usename, String pwd, int port) {
        CommonResp commonResp = new CommonResp();

        SSHUtils rec = new SSHUtils(ip, usename, pwd, port);//192.168.1.72  103.27.109.200

        String result = rec.execute(targetPath);

        System.out.println("执行脚本结果" + result);

        System.out.println(result);

        if (null == result) {
            commonResp.setCode("-1");
            commonResp.setMsg("fail");

        } else {
            commonResp.setMsg("success");
            commonResp.setCode("1");
        }
        return commonResp;
    }


    /**
     * 执行脚本
     *
     * @param cmd
     * @param ip
     * @param usename
     * @param pwd
     * @return
     */
    private static CommonResp excuteShell1(String cmd, String ip, String usename, String pwd, int port) {
        CommonResp commonResp = new CommonResp();
        SSHUtils rec = new SSHUtils(ip, usename, pwd, port);//192.168.1.72  103.27.109.200

        String result = rec.execute1(cmd);
        System.out.println("执行脚本结果" + result);
        System.out.println(result);
        if (null == result) {
            LOGGER.error("执行脚本失败");
            commonResp.setCode("-1");
            commonResp.setMsg("fail");

        } else {
            LOGGER.error("执行脚本成功");
            commonResp.setMsg("success");
            commonResp.setCode("1");
        }
        return commonResp;
    }


    /**
     * 训练回调
     *
     * @param content
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/copyBack", method = RequestMethod.POST)
    public void copyBack(@RequestBody ResultBack content) throws IOException, Exception {
        int status;
        if (content.getMessage().equalsIgnoreCase("success")) {
            status = 2;
        } else {
            status = 6;
        }
        trainAndStartMapper.updateByName(content.getName(), status);
    }

    /**
     * 生成端口号
     *
     * @return
     */
    private int generatePort() {
        Integer port = trainAndStartMapper.queryMaxPort();
        if (port == null) {
            return 26001;
        }
        return port + 1;
    }


    public void executeLinuxCmd(String cmd) {
        System.out.println("got cmd job : " + cmd);
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec(cmd);
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            // System.out.println("[check] now size \n"+bs.readLine());
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[8192];
            for (int n; (n = in.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
            System.out.println("job result [" + out.toString() + "]");
            in.close();
            // process.waitFor();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getModelList", method = RequestMethod.GET)
    public CommonResp getList(@RequestParam(required = false) String name, @RequestParam(required = false) Integer status) {
        CommonResp commonResp = new CommonResp();
        if (StringUtils.isBlank(name) || name.trim().equalsIgnoreCase("")) {
            name = null;
        }

        List<Model> list = trainAndStartMapper.queryModelInfos(status, name);
        if (list != null && !list.isEmpty()) {

            for (Model model : list) {
                if (model != null) {
                    model.setLabel(new String(Base64.getDecoder().decode(model.getName())));
                    model.setIp("121.40.171.228");
                }
            }

        }
        if (list != null && !list.isEmpty()) {
            commonResp.setCode("0");
            commonResp.setObject(list);
            commonResp.setMsg("success");
        } else {
            commonResp.setCode("-1");
            commonResp.setObject(null);
            commonResp.setMsg("无数据");
        }

        return commonResp;
    }


    /**
     * @return
     */
    @RequestMapping(value = "/getListById", method = RequestMethod.GET)
    public CommonResp getListById(@RequestParam String modelName) {
        CommonResp commonResp = new CommonResp();

        List<Model> models = trainAndStartMapper.queryModelInfo(modelName);
        if (models.isEmpty()) {
            commonResp.setMsg("数据不存在");
            commonResp.setCode("-1");
            return commonResp;
        }
        Model model = models.get(0);
        if (model != null) {
            model.setIp("121.40.171.228");
            commonResp.setCode("0");
            commonResp.setObject(model);
            commonResp.setMsg("success");
        } else {
            commonResp.setCode("-1");
            commonResp.setObject(null);
            commonResp.setMsg("无数据");
        }

        return commonResp;
    }

    private static String getNowTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + sdf.format(d));
        return sdf.format(d);
    }

//    public static void main(String[] args) {
//        getNowTime();
//    }
}
