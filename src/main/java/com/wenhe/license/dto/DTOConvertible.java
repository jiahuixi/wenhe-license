package com.wenhe.license.dto;

public interface DTOConvertible<T> {
    /**
     * 数据转换
     * @return
     */
    T convert();
}
