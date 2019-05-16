package com.wenhe.license.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class LinuxLicenseSystemInfo extends AbstractLicenseSystemInfo {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static final Logger logger = LoggerFactory.getLogger(LinuxLicenseSystemInfo.class);

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 3-1];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 3] = hexArray[v >>> 4];
            hexChars[j * 3 + 1] = hexArray[v & 0x0F];
            if (j != bytes.length - 1) {
                hexChars[j * 3 + 2] = ':';
            }
        }
        return String.valueOf(hexChars);
    }

    @Override
    public Set<String> getMacAddress() {
        Set<String> macAddresses = new HashSet<>();
        try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaceEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
                logger.debug(networkInterface.toString());
                if (networkInterface.getHardwareAddress() != null) {
                    macAddresses.add(bytesToHex(networkInterface.getHardwareAddress()));
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return macAddresses;
    }

    @Override
    public String getCpuID() {
        return null;
    }

    @Override
    public String getMainBoardSerial() {
        return null;
    }
}
