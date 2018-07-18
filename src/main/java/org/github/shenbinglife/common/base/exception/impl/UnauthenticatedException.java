package org.github.shenbinglife.common.base.exception.impl;


import org.github.shenbinglife.common.base.exception.UncheckedException;

/**
 * 未认证异常
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
public class UnauthenticatedException extends UncheckedException {

    public UnauthenticatedException() {
    }

    public UnauthenticatedException(String message) {
        super(message);
    }

    public UnauthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthenticatedException(Throwable cause) {
        super(cause);
    }
}
