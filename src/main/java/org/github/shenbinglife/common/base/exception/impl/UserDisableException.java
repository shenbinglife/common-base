package org.github.shenbinglife.common.base.exception.impl;

import org.github.shenbinglife.common.base.exception.UncheckedException;

/**
 * 用户被禁用的异常
 *
 * @author SHEN
 * @version 2017/6/27
 * @since since
 */
public class UserDisableException extends UncheckedException {

    public UserDisableException() {
    }

    public UserDisableException(String message) {
        super(message);
    }

    public UserDisableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDisableException(Throwable cause) {
        super(cause);
    }
}
