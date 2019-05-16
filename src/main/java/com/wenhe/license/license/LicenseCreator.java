package com.wenhe.license.license;

import com.wenhe.license.parameters.CustomKeyStoreParam;
import com.wenhe.license.parameters.LicenseCreatorParameter;
import de.schlichtherle.license.*;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.util.Objects;
import java.util.prefs.Preferences;

public class LicenseCreator {

    private final static X500Principal DEFAULT_HOLDER_AND_ISSUER = new X500Principal("CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN");

    private LicenseCreatorParameter parameter;
    private File licFile;

    public LicenseCreator(LicenseCreatorParameter parameter) {
        Objects.requireNonNull(parameter);
        this.parameter = parameter;
        this.licFile = new File(parameter.getLicensePath());
    }

    private static LicenseContent transfer(LicenseCreatorParameter parameter) {
        LicenseContent content = new LicenseContent();
        content.setConsumerAmount(parameter.getConsumerAmount());
        content.setConsumerType(parameter.getConsumerType());
        content.setInfo(parameter.getInfo());
        content.setIssued(parameter.getIssued());
        content.setNotAfter(parameter.getNotAfter());
        content.setNotBefore(parameter.getNotBefore());
        content.setSubject(parameter.getSubject());
        content.setExtra(parameter.getExtra());
        content.setHolder(DEFAULT_HOLDER_AND_ISSUER);
        content.setIssuer(DEFAULT_HOLDER_AND_ISSUER);
        return content;
    }

    public void create() throws Exception {
        LicenseManager licenseManager = new CustomLicenseManager(licenseParam());
        licenseManager.store(transfer(parameter), licFile);
    }

    private LicenseParam licenseParam() {
        Class<LicenseCreator> clazz = LicenseCreator.class;
        Preferences pre = Preferences.userNodeForPackage(clazz);
        //设置对证书内容加密的秘钥
        CipherParam cipherParam = new DefaultCipherParam(parameter.getStorePwd());
        /**
         * clazz 从哪个类Class.getResource()获得密钥库
         * priPath 从哪个类Class.getResource()获得密钥库
         * priAlias 密钥库的别名
         * keystorePwd 密钥库存储密码
         * privateKeyPwd 密钥库密码
         */
        KeyStoreParam privateStoreParam = new CustomKeyStoreParam(
                clazz,
                parameter.getKeyStoreResourcePath(),
                parameter.getAlias()
                , parameter.getStorePwd(),
                parameter.getKeyPwd());
        //返回生成证书时需要的参数
        LicenseParam licenseParam = new DefaultLicenseParam(
                parameter.getSubject(),
                pre,
                privateStoreParam,
                cipherParam);
        return licenseParam;
    }
}
