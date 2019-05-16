package com.wenhe.license.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "license")
public class LicenseBean {
    private String storePwd;
    private String keyPwd;
    private String alias;
    private String keyStoreResourcePath;
    private String licensePath;

    public String getStorePwd() {
        return storePwd;
    }

    public LicenseBean setStorePwd(String storePwd) {
        this.storePwd = storePwd;
        return this;
    }

    public String getKeyPwd() {
        return keyPwd;
    }

    public LicenseBean setKeyPwd(String keyPwd) {
        this.keyPwd = keyPwd;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public LicenseBean setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getKeyStoreResourcePath() {
        return keyStoreResourcePath;
    }

    public LicenseBean setKeyStoreResourcePath(String keyStoreResourcePath) {
        this.keyStoreResourcePath = keyStoreResourcePath;
        return this;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public LicenseBean setLicensePath(String licensePath) {
        this.licensePath = licensePath;
        return this;
    }

    @Override
    public String toString() {
        return "LicenseBean{" +
                "alias='" + alias + '\'' +
                ", keyStoreResourcePath='" + keyStoreResourcePath + '\'' +
                '}';
    }
}
