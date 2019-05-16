package com.wenhe.license.license;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public abstract class AbstractLicenseSystemInfo {

    private static final Logger logger = LoggerFactory.getLogger(AbstractLicenseSystemInfo.class);

    public static AbstractLicenseSystemInfo get() {
        String osName = System.getProperty("os.name");

        logger.debug("Operating inside {}", osName);
        switch (osName) {
            case "Linux":
                return new LinuxLicenseSystemInfo();
            default:
                return new DefaultLicenseSystemInfo();
        }
    }

    public abstract Set<String> getMacAddress();

    public abstract String getCpuID();

    public abstract String getMainBoardSerial();

//    /**
//     *
//     * @param bytes
//     * @param joiner
//     * @return
//     */
//    private static String hexString(byte[] bytes,String joiner) {
//        String hex =
//        StringBuilder sb =new StringBuilder();
//        for (int i = 0; i < bytes.length; i=i+6) {
//            String s =  String.valueOf(bytes, 0, 6);
//            sb.append(Integer.valueOf(s, 16));
//            String.valueof
//        }
//        sb.
//    }
}
