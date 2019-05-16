package com.wenhe.license.creator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenhe.license.validator.LicenseVerifyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LicenseCreatorParameters {
    private static final Logger logger = LoggerFactory.getLogger(LicenseCreatorParameters.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static LicenseCreatorParameter<LicenseVerifyModel> create(String yamlPath) {
        Yaml yaml = new Yaml();

        Object obj = null;
        try {
            obj = yaml.load(new FileInputStream(yamlPath));
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
        if (obj instanceof LinkedHashMap) {
            LinkedHashMap map = (LinkedHashMap) obj;
            LicenseCreatorParameter<LicenseVerifyModel> parameter = new LicenseCreatorParameter<>();


            parameter.setAlias(String.valueOf(map.get("alias")));
            parameter.setKeyPwd(String.valueOf(map.get("keyPwd")));
            parameter.setKeyStoreResourcePath(String.valueOf(map.get("keyStoreResourcePath")));
            parameter.setLicensePath(String.valueOf(map.get("licensePath")));
            parameter.setStorePwd(String.valueOf(map.get("storePwd")));


            parameter.setConsumerAmount((int) map.get("consumerAmount"));
            parameter.setConsumerType(String.valueOf(map.get("consumerType")));


            parameter.setIssued((Date) (map.get("issued")));
            parameter.setNotAfter((Date) (map.get("notAfter")));
            parameter.setNotBefore((Date) (map.get("notBefore")));


            parameter.setSubject(String.valueOf(map.get("subject")));

            Map<String, Object> extra = (LinkedHashMap<String, Object>) map.get("extra");
            LicenseVerifyModel model = new LicenseVerifyModel();
            model.setMacAddress((List<String>) extra.get("macAddress"));
            model.setCpuID(obj2String(extra.get("cpuID")));

            model.setMainBoardSerial(obj2String(extra.get("mainBoardSerial")));

            parameter.setExtra(model);

            parameter.setInfo(String.valueOf(map.get("info")));

            logger.debug("Loaded Param : {}", parameter);
            return parameter;

        }
        logger.debug("Yaml:{}", obj);
        throw new IllegalArgumentException();
    }


    private static String obj2String(Object obj) {
        return obj == null ? null : String.valueOf(obj);
    }
}
