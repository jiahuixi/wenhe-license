package com.wenhe.license.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenhe.license.creator.LicenseCreatorParameter;
import com.wenhe.license.validator.LicenseVerifyModel;

import java.util.Date;

public class LicenseCreatorParameterInputDTO implements DTOConvertible<LicenseCreatorParameter<LicenseVerifyModel>>, Verifiable {

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
    private LicenseVerifyModel extra;


    @Override
    public LicenseCreatorParameter<LicenseVerifyModel> convert() {
        LicenseCreatorParameter<LicenseVerifyModel> parameter = new LicenseCreatorParameter<>();
        parameter.setSubject(this.subject);
        parameter.setConsumerType(this.consumerType);
        parameter.setConsumerAmount(this.consumerAmount);
        parameter.setIssued(this.issued);
        parameter.setNotBefore(this.notBefore);
        parameter.setNotAfter(this.notAfter);
        parameter.setInfo(this.info);
        parameter.setExtra(this.extra);
        return parameter;
    }

    @Override
    public boolean validate() {
        return false;
    }

    public String getSubject() {
        return subject;
    }

    public LicenseCreatorParameterInputDTO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public int getConsumerAmount() {
        return consumerAmount;
    }

    public LicenseCreatorParameterInputDTO setConsumerAmount(int consumerAmount) {
        this.consumerAmount = consumerAmount;
        return this;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public LicenseCreatorParameterInputDTO setConsumerType(String consumerType) {
        this.consumerType = consumerType;
        return this;
    }

    public Date getIssued() {
        return issued;
    }

    public LicenseCreatorParameterInputDTO setIssued(Date issued) {
        this.issued = issued;
        return this;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public LicenseCreatorParameterInputDTO setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
        return this;
    }

    public Date getNotAfter() {
        return notAfter;
    }

    public LicenseCreatorParameterInputDTO setNotAfter(Date notAfter) {
        this.notAfter = notAfter;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public LicenseCreatorParameterInputDTO setInfo(String info) {
        this.info = info;
        return this;
    }

    public LicenseVerifyModel getExtra() {
        return extra;
    }

    public LicenseCreatorParameterInputDTO setExtra(LicenseVerifyModel extra) {
        this.extra = extra;
        return this;
    }

    @Override
    public String toString() {
        return "LicenseCreatorParameterInputDTO{" +
                "subject='" + subject + '\'' +
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
