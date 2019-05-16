package com.wenhe.license.controller;

import com.wenhe.license.config.LicenseBean;
import com.wenhe.license.dto.LicenseCreatorParameterInputDTO;
import com.wenhe.license.license.LicenseCreator;
import com.wenhe.license.parameters.LicenseCreatorParameter;
import com.wenhe.license.license.LicenseValidator;
import com.wenhe.license.license.LicenseVerifyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
public class LicenseCreateController {

    private static final Logger logger = LoggerFactory.getLogger(LicenseCreateController.class);

    @Autowired
    LicenseBean licenseBean;

    @Autowired
    LicenseValidator licenseValidator;

    private static String licensePath() {
        String path = "license/" + UUID.randomUUID() + ".lic";
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return path;
    }

    @PostMapping("/license")
    public ResponseEntity<Resource> create(@RequestBody LicenseCreatorParameterInputDTO parameter) throws Exception {
        parameter.validate();
        LicenseCreatorParameter<LicenseVerifyModel> licenseCreatorParameter = wrap(parameter.convert());

        logger.debug("license : {}", licenseCreatorParameter);
        LicenseCreator creator = new LicenseCreator(licenseCreatorParameter);
        creator.create();

        Resource resource = new InputStreamResource(new FileInputStream(licenseCreatorParameter.getLicensePath()));
        return down("license.lic", resource);
    }

    @ResponseBody
    @PutMapping("/license/verify")
    public String verify(@RequestParam("license") String licensePath) throws Exception {
        licenseValidator.install(licensePath);
        licenseValidator.verify();
        return "success";
    }


    @ResponseBody
    @ResponseStatus
    @ExceptionHandler(Exception.class)
    private String error(Exception e) {
        logger.error("Server Exception!", e);
        return "服务异常,请重试!";
    }

//    @ResponseBody
//    @ResponseStatus
//    @ExceptionHandler(LicenseContentException.class)
//    private String licenseContentException(LicenseContentException e) {
//        return e.getMessage();
//    }


    private LicenseCreatorParameter<LicenseVerifyModel> wrap(LicenseCreatorParameter<LicenseVerifyModel> licenseCreatorParameter) {
        licenseCreatorParameter.setStorePwd(licenseBean.getStorePwd());
        licenseCreatorParameter.setKeyPwd(licenseBean.getKeyPwd());
        licenseCreatorParameter.setAlias(licenseBean.getAlias());
        licenseCreatorParameter.setKeyStoreResourcePath(licenseBean.getKeyStoreResourcePath());
        licenseCreatorParameter.setLicensePath(licensePath());
        return licenseCreatorParameter;
    }

    private ResponseEntity<Resource> down(String filename, Resource resource) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("charset", "utf-8");
        //设置下载文件名
        filename = URLEncoder.encode(filename, "UTF-8");
        headers.add("Content-Disposition", "attachment;filename=\"" + filename + "\"");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload")).body(resource);
    }

}
