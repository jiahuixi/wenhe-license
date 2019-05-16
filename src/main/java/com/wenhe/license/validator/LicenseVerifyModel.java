package com.wenhe.license.validator;

import java.io.Serializable;
import java.util.List;

public class LicenseVerifyModel implements Serializable {

    private static final long serialVersionUID = 67132223738862562L;
    private List<String> macAddress;

    private String cpuID;
    private String mainBoardSerial;

    public List<String> getMacAddress() {
        return macAddress;
    }

    @Override
    public String toString() {
        return "LicenseVerifyModel{" +
                "macAddress=" + macAddress +
                ", cpuID='" + cpuID + '\'' +
                ", mainBoardSerial='" + mainBoardSerial + '\'' +
                '}';
    }

    public void setMacAddress(List<String> macAddress) {
        this.macAddress = macAddress;
    }

    public String getCpuID() {
        return cpuID;
    }

    public void setCpuID(String cpuID) {
        this.cpuID = cpuID;
    }

    public String getMainBoardSerial() {
        return mainBoardSerial;
    }

    public void setMainBoardSerial(String mainBoardSerial) {
        this.mainBoardSerial = mainBoardSerial;
    }
}
