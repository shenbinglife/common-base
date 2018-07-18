package io.github.shenbinglife.common.base.exception.impl;

/**
 * 认证失败异常
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
public class AuthenticationFailedException extends UnauthenticatedException {

    public AuthenticationFailedException() {
    }

    public AuthenticationFailedException(String message) {
        super(message);
    }

    public AuthenticationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationFailedException(Throwable cause) {
        super(cause);
    }
}
