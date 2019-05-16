package com.wenhe.license.validator;

import de.schlichtherle.license.*;
import de.schlichtherle.xml.GenericCertificate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class CustomLicenseManager extends LicenseManager {

    private static final Logger logger = LoggerFactory.getLogger(CustomLicenseManager.class);
    private static final AbstractLicenseSystemInfo systemInfo = AbstractLicenseSystemInfo.get();
    //XML编码
    private static final String XML_CHARSET = "UTF-8";
    //默认BUFSIZE
    private static final int DEFAULT_BUFSIZE = 8 * 1024;

    public CustomLicenseManager(LicenseParam param) {
        super(param);
    }

    @Override
    protected synchronized byte[] create(LicenseContent content, LicenseNotary notary) throws Exception {
        initialize(content);
        validateOnCreate(content);
        final GenericCertificate certificate = notary.sign(content);
        return getPrivacyGuard().cert2key(certificate);
    }

    private void validateOnCreate(LicenseContent content) throws LicenseContentException {
        super.validate(content);
    }

    @Override
    protected synchronized void validate(LicenseContent content) throws LicenseContentException {
        super.validate(content);

        LicenseVerifyModel licenseVerifyModel = (LicenseVerifyModel) content.getExtra();
        logger.debug("Verify Param: {}", licenseVerifyModel);
        checkMacAddress(licenseVerifyModel.getMacAddress(), systemInfo.getMacAddress());
//        checkCpuID(licenseVerifyModel.getCpuID(), systemInfo.getCpuID());
//        checkMainBoard(licenseVerifyModel.getMainBoardSerial(), systemInfo.getMainBoardSerial());
    }

    private void checkMacAddress(List<String> auth, Set<String> checked) throws LicenseContentException {
        logger.debug("Auth Mac Address: {}  VS Local Mac Address: {}", auth, checked);
        if (auth != null && !auth.isEmpty()) {
            if (checked != null && !checked.isEmpty()) {
                for (String s : checked) {
                    if (auth.contains(s.toLowerCase()) || auth.contains(s.toUpperCase())) {
                        return;
                    }
                }
            }
            throw new LicenseContentException("当前服务器的Mac地址没在授权范围内");
        }
    }

    private void checkCpuID(String author, String checked) throws LicenseContentException {
        if (StringUtils.isBlank(author) || author.equals(checked)) {
            return;
        } else {
            throw new LicenseContentException("当前服务器的 CPU ID 没在授权范围内");
        }
    }

    private void checkMainBoard(String auth, String checked) throws LicenseContentException {
        if (StringUtils.isBlank(auth) || auth.equals(checked)) {
            return;
        } else {
            throw new LicenseContentException("当前主板序列号没在授权范围内");
        }

    }


//    private Object load(String encoded) {
//        BufferedInputStream inputStream = null;
//        XMLDecoder decoder = null;
//        try {
//            inputStream = new BufferedInputStream(new ByteArrayInputStream(encoded.getBytes(XML_CHARSET)));
//
//            decoder = new XMLDecoder(new BufferedInputStream(inputStream, DEFAULT_BUFSIZE), null, null);
//
//            return decoder.readObject();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (decoder != null) {
//                    decoder.close();
//                }
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (Exception e) {
//                logger.error("XMLDecoder解析XML失败", e);
//            }
//        }
//
//        return null;
//    }
}
