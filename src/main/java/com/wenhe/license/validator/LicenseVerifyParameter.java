package com.wenhe.license.validator;

public class LicenseVerifyParameter {

    private String storePwd;
    private String alias;
    private String keyStoreResourcePath;
    private String licensePath;

    private String subject;

    public String getSubject() {
        return subject;
    }

    public LicenseVerifyParameter setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getStorePwd() {
        return storePwd;

    }

    public LicenseVerifyParameter setStorePwd(String storePwd) {
        this.storePwd = storePwd;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public LicenseVerifyParameter setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getKeyStoreResourcePath() {
        return keyStoreResourcePath;
    }

    public LicenseVerifyParameter setKeyStoreResourcePath(String keyStoreResourcePath) {
        this.keyStoreResourcePath = keyStoreResourcePath;
        return this;
    }

    public String getLicensePath() {
        return licensePath;
    }

    @Override
    public String toString() {
        return "LicenseVerifyParameter{" +
                "storePwd='" + storePwd + '\'' +
                ", alias='" + alias + '\'' +
                ", keyStoreResourcePath='" + keyStoreResourcePath + '\'' +
                ", licensePath='" + licensePath + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    public LicenseVerifyParameter setLicensePath(String licensePath) {
        this.licensePath = licensePath;
        return this;
    }
}
