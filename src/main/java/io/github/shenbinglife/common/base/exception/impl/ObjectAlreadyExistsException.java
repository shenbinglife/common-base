package io.github.shenbinglife.common.base.exception.impl;


import io.github.shenbinglife.common.base.exception.UncheckedException;

/**
 * 自定义异常:对象已存在异常
 *
 * @author SHEN
 * @version 2017/6/16
 * @since since
 */
public class ObjectAlreadyExistsException extends UncheckedException {
    public ObjectAlreadyExistsException() {
    }

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }

    public ObjectAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
