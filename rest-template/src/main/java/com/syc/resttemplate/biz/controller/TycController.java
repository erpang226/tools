package com.syc.resttemplate.biz.controller;

import com.alibaba.fastjson.JSONObject;
import com.syc.resttemplate.biz.service.TycService;
import com.syc.resttemplate.config.BizException;
import com.syc.resttemplate.config.ReturnEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tyc")
public class TycController {

    @Autowired
    private TycService tycService;

    @RequestMapping("/")
    public JSONObject index() {
        return JSONObject.parseObject("{\"name\":\"天眼查\"}");
    }


    @PostMapping(value = "/getEnterpriseBasicInfo")
    public JSONObject getEnterpriseBasicInfo(@RequestParam("name") String name, @RequestParam("user") String user) {
        try {
            return tycService.getEnterpriseBasicInfo(name, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getDCDY")
    public JSONObject getDCDY(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getDCDY(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getXZCF")
    public JSONObject getXZCF(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getXZCF(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getJYYC")
    public JSONObject getJYYC(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getJYYC(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getKTGG")
    public JSONObject getKTGG(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getKTGG(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }


    @PostMapping(value = "/getBZXR")
    public JSONObject getBZXR(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getBZXR(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getFYGG")
    public JSONObject getFYGG(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getFYGG(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getFYPJ")
    public JSONObject getFYPJ(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getFYPJ(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getSXR")
    public JSONObject getSXR(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getSXR(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getSMRZ")
    public JSONObject getSMRZ(@RequestParam("name") String name, @RequestParam("idcard") String idcard, @RequestParam("user") String user) {
        try {
            return tycService.getSMRZ(name, idcard, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getGQCZ")
    public JSONObject getGQCZ(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum, @RequestParam("user") String user) {
        try {
            return tycService.getGQCZ(name, pageNum, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    /**
     * 身份证正反面信息识别
     *
     * @param imageId 图片id
     * @return
     */
    @PostMapping(value = "/getSFZSB")
    public JSONObject getSFZSB(String imageId, @RequestParam("user") String user) {
        try {
            return tycService.getSFZSB(imageId, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getYHKSB")
    public JSONObject getYHKSB(String imageId, @RequestParam("user") String user) {
        try {
            return tycService.getYHKSB(imageId, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }
}
