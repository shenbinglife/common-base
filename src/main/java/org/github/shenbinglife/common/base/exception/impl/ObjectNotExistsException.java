package org.github.shenbinglife.common.base.exception.impl;


import org.github.shenbinglife.common.base.exception.UncheckedException;

/**
 * 自定义异常：对象不存在异常
 *
 * @author SHEN
 * @version 2017/6/16
 * @since since
 */
public class ObjectNotExistsException extends UncheckedException {
    public ObjectNotExistsException() {
    }

    public ObjectNotExistsException(String message) {
        super(message);
    }

    public ObjectNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotExistsException(Throwable cause) {
        super(cause);
    }
}
