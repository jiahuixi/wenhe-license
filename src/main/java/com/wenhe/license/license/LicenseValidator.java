package com.wenhe.license.license;

import com.wenhe.license.parameters.CustomKeyStoreParam;
import com.wenhe.license.parameters.LicenseVerifyParameter;
import de.schlichtherle.license.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Objects;
import java.util.prefs.Preferences;

public class LicenseValidator {
    private static final Logger logger = LoggerFactory.getLogger(LicenseValidator.class);
    private final LicenseManager licenseManager;
    private LicenseVerifyParameter parameter;
    private File licFile;

    public LicenseValidator(LicenseVerifyParameter parameter) {
        Objects.requireNonNull(parameter);
        this.parameter = parameter;
        if (StringUtils.isNotBlank(parameter.getLicensePath())) {
            this.licFile = new File(parameter.getLicensePath());
        }
        this.licenseManager = new CustomLicenseManager(createParam(parameter));

    }

    public synchronized void install() throws Exception {
        licenseManager.uninstall();
        if (logger.isDebugEnabled()) {
            logger.debug("license path: {}", licFile.getAbsolutePath());
        }
        licenseManager.install(licFile);
    }

    public synchronized void install(String licensePath) throws Exception {
        licenseManager.uninstall();
        licenseManager.install(new File(licensePath));
    }

    public void verify() throws Exception {
        licenseManager.verify();
    }

    private LicenseParam createParam(LicenseVerifyParameter parameter) {

        logger.debug("Loading Parameter: {}", parameter);
        Class clazz = LicenseValidator.class;
        Preferences pre = Preferences.userNodeForPackage(clazz);

        CipherParam cipherParam = new DefaultCipherParam(parameter.getStorePwd());

        KeyStoreParam publicStoreParam = new CustomKeyStoreParam(
                clazz, parameter.getKeyStoreResourcePath(), parameter.getAlias()
                , parameter.getStorePwd(), null);

        return new DefaultLicenseParam(
                parameter.getSubject(), pre, publicStoreParam, cipherParam);
    }
}
