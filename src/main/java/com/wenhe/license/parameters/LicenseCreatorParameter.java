package com.wenhe.license.parameters;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LicenseCreatorParameter<T> {

    private String storePwd;
    private String keyPwd;
    private String alias;
    private String keyStoreResourcePath;
    private String licensePath;


    // license content


    private String subject;
    private int consumerAmount;
    private String consumerType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issued;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date notBefore;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date notAfter;
    private String info;
    private T extra;

    public String getStorePwd() {
        return storePwd;
    }

    public LicenseCreatorParameter<T> setStorePwd(String storePwd) {
        this.storePwd = storePwd;
        return this;
    }

    public String getKeyPwd() {
        return keyPwd;
    }

    public LicenseCreatorParameter<T> setKeyPwd(String keyPwd) {
        this.keyPwd = keyPwd;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public LicenseCreatorParameter<T> setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getKeyStoreResourcePath() {
        return keyStoreResourcePath;
    }

    public LicenseCreatorParameter<T> setKeyStoreResourcePath(String keyStoreResourcePath) {
        this.keyStoreResourcePath = keyStoreResourcePath;
        return this;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public LicenseCreatorParameter<T> setLicensePath(String licensePath) {
        this.licensePath = licensePath;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public LicenseCreatorParameter<T> setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public int getConsumerAmount() {
        return consumerAmount;
    }

    public LicenseCreatorParameter<T> setConsumerAmount(int consumerAmount) {
        this.consumerAmount = consumerAmount;
        return this;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public LicenseCreatorParameter<T> setConsumerType(String consumerType) {
        this.consumerType = consumerType;
        return this;
    }

    public Date getIssued() {
        return issued;
    }

    public LicenseCreatorParameter<T> setIssued(Date issued) {
        this.issued = issued;
        return this;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public LicenseCreatorParameter<T> setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
        return this;
    }

    public Date getNotAfter() {
        return notAfter;
    }

    public LicenseCreatorParameter<T> setNotAfter(Date notAfter) {
        this.notAfter = notAfter;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public LicenseCreatorParameter<T> setInfo(String info) {
        this.info = info;
        return this;
    }

    public T getExtra() {
        return extra;
    }

    public LicenseCreatorParameter<T> setExtra(T extra) {
        this.extra = extra;
        return this;
    }

    @Override
    public String toString() {
        return "LicenseCreatorParameter{" +
                "storePwd='" + storePwd + '\'' +
                ", keyPwd='" + keyPwd + '\'' +
                ", alias='" + alias + '\'' +
                ", keyStoreResourcePath='" + keyStoreResourcePath + '\'' +
                ", licensePath='" + licensePath + '\'' +
                ", subject='" + subject + '\'' +
                ", consumerAmount=" + consumerAmount +
                ", consumerType='" + consumerType + '\'' +
                ", issued=" + issued +
                ", notBefore=" + notBefore +
                ", notAfter=" + notAfter +
                ", info='" + info + '\'' +
                ", extra=" + extra +
                '}';
    }
}
