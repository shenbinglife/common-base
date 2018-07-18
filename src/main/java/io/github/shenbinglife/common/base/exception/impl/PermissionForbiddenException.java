package io.github.shenbinglife.common.base.exception.impl;


import io.github.shenbinglife.common.base.exception.UncheckedException;

/**
 * 权限不足异常
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
public class PermissionForbiddenException extends UncheckedException {
    public PermissionForbiddenException() {
    }

    public PermissionForbiddenException(String message) {
        super(message);
    }

    public PermissionForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionForbiddenException(Throwable cause) {
        super(cause);
    }
}
