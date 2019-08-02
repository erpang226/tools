package com.syc.javacodegenerator.controller;

import com.syc.javacodegenerator.entity.BladeRole;
import com.syc.javacodegenerator.service.BladeRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author syc
 * Date  2019-08-02
 */
@RestController
@RequestMapping(value = "/bladeRole")
public class BladeRoleController {
    @Autowired
    private BladeRoleService bladeRoleService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<BladeRole> bladeRoles = bladeRoleService.findAllList();
        return bladeRoles;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Object get(@RequestParam String id) {
        BladeRole bladeRole = bladeRoleService.get(id);
        return bladeRole;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody BladeRole bladeRole) {
        if (bladeRoleService.insert(bladeRole) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<BladeRole> bladeRoles) {
        if (bladeRoleService.insertBatch(bladeRoles) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody BladeRole bladeRole) {
        if (bladeRoleService.update(bladeRole) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody BladeRole bladeRole) {
        if (bladeRoleService.delete(bladeRole) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

}
