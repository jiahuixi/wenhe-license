package com.wenhe.license.config;

import com.wenhe.license.validator.LicenseValidator;
import com.wenhe.license.validator.LicenseVerifyParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public LicenseValidator licenseValidator(@Autowired PublicStoreBean publicStoreBean) {
        LicenseVerifyParameter parameter = new LicenseVerifyParameter();
        parameter.setAlias(publicStoreBean.getAlias());
        parameter.setKeyStoreResourcePath(publicStoreBean.getKeyStoreResourcePath());
        parameter.setStorePwd(publicStoreBean.getStorePwd());
        parameter.setSubject(publicStoreBean.getSubject());
        parameter.setLicensePath(publicStoreBean.getLicensePath());
        return new LicenseValidator(parameter);
    }
}
