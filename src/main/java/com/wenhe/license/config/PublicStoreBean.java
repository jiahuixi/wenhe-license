package com.wenhe.license.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("public-store")
public class PublicStoreBean {
    private String storePwd;
    private String alias;
    private String keyStoreResourcePath;

    private String subject;

    private String licensePath;

    @Override
    public String toString() {
        return "PublicStoreBean{" +
                "alias='" + alias + '\'' +
                ", keyStoreResourcePath='" + keyStoreResourcePath + '\'' +
                ", subject='" + subject + '\'' +
                ", licensePath='" + licensePath + '\'' +
                '}';
    }

    public String getLicensePath() {
        return licensePath;
    }

    public PublicStoreBean setLicensePath(String licensePath) {
        this.licensePath = licensePath;
        return this;
    }

    public String getStorePwd() {
        return storePwd;
    }

    public PublicStoreBean setStorePwd(String storePwd) {
        this.storePwd = storePwd;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public PublicStoreBean setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getKeyStoreResourcePath() {
        return keyStoreResourcePath;
    }

    public PublicStoreBean setKeyStoreResourcePath(String keyStoreResourcePath) {
        this.keyStoreResourcePath = keyStoreResourcePath;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public PublicStoreBean setSubject(String subject) {
        this.subject = subject;
        return this;
    }

}
