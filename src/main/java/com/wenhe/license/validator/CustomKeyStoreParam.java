package com.wenhe.license.validator;

import de.schlichtherle.license.AbstractKeyStoreParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomKeyStoreParam extends AbstractKeyStoreParam {

    private final String resource;
    private String alias;
    private String storePwd;
    private String keyPwd;

    public CustomKeyStoreParam(Class clazz, String resource, String alias, String storePwd, String keyPwd) {
        super(clazz, resource);
        this.resource = resource;
        this.alias = alias;
        this.storePwd = storePwd;
        this.keyPwd = keyPwd;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public InputStream getStream() throws IOException {
        return new FileInputStream(resource);
    }

    @Override
    public String getStorePwd() {
        return storePwd;
    }

    @Override
    public String getKeyPwd() {
        return keyPwd;
    }
}
