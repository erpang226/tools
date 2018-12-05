package com.syc.resttemplate.biz.controller;

import com.alibaba.fastjson.JSONObject;
import com.syc.resttemplate.biz.service.SjbService;
import com.syc.resttemplate.config.BizException;
import com.syc.resttemplate.config.ReturnEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sjb")
public class SjbController {

    @Autowired
    private SjbService sjbService;

    @RequestMapping("/{id}")
    public String index(@PathVariable String id) {
        return "数据宝 " + id;
    }


    @PostMapping(value = "/getEnterpriseBasicInfo")
    public JSONObject getEnterpriseBasicInfo(@RequestParam("name") String name, @RequestParam("type") String type,
                                             @RequestParam("entType") String entType, @RequestParam("user") String user) {
        try {
            return sjbService.getEnterpriseBasicInfo(name, type, entType, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getDCDY")
    public JSONObject getDCDY(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {

        try {
            return sjbService.getDCDY(name, skip, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getXZCF")
    public JSONObject getXZCF(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {
        try {
            return sjbService.getXZCF(name, skip, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getJYYC")
    public JSONObject getJYYC(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {
        try {
            return sjbService.getJYYC(name, skip, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getKTGG")
    public JSONObject getKTGG(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {
        try {
            return sjbService.getKTGG(name, skip, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }


    @PostMapping(value = "/getBZXR")
    public JSONObject getBZXR(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {
        try {
            return sjbService.getBZXR(name, skip, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getFYGG")
    public JSONObject getFYGG(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {
        try {
            return sjbService.getFYGG(name, skip, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getFYPJ")
    public JSONObject getFYPJ(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {
        try {
            return sjbService.getFYPJ(name, skip, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getSXR")
    public JSONObject getSXR(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {
        try {
            return sjbService.getSXR(name, skip, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getSMRZ")
    public JSONObject getSMRZ(@RequestParam("name") String name, @RequestParam("idcard") String idcard, @RequestParam("user") String user) {
        try {
            return sjbService.getSMRZ(name, idcard, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getGQCZ")
    public JSONObject getGQCZ(@RequestParam("name") String name, @RequestParam("skip") int skip, @RequestParam("user") String user) {
        try {
            return sjbService.getGQCZ(name, skip, user);
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
            return sjbService.getSFZSB(imageId, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

    @PostMapping(value = "/getYHKSB")
    public JSONObject getYHKSB(String imageId, @RequestParam("user") String user) {
        try {
            return sjbService.getYHKSB(imageId, user);
        } catch (BizException e) {
            return ReturnEnum.FAIL.errorResult();
        }
    }

}
