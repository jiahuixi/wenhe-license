package com.wenhe.license.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.LinkedHashMap;

public class LicenseVerifyParameters {

    private static final Logger logger = LoggerFactory.getLogger(LicenseVerifyParameters.class);


    public static LicenseVerifyParameter create(String yamlPath) {
        Yaml yaml = new Yaml();

        Object obj = null;
        try {
            obj = yaml.load(new FileInputStream(yamlPath));
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
        if (obj instanceof LinkedHashMap) {
            LinkedHashMap map = (LinkedHashMap) obj;
            LicenseVerifyParameter parameter = new LicenseVerifyParameter();


            parameter.setAlias(String.valueOf(map.get("alias")));
            parameter.setKeyStoreResourcePath(String.valueOf(map.get("keyStoreResourcePath")));
            parameter.setLicensePath(String.valueOf(map.get("licensePath")));
            parameter.setStorePwd(String.valueOf(map.get("storePwd")));
            parameter.setSubject(String.valueOf(map.get("subject")));

            logger.debug("Loaded Param: {}",parameter);
            return parameter;

        }
        logger.debug("Yaml:{}", obj);
        throw new IllegalArgumentException();
    }
}
