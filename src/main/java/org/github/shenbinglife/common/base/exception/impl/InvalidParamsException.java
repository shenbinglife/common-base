package org.github.shenbinglife.common.base.exception.impl;


import org.github.shenbinglife.common.base.exception.ICodeException;
import org.github.shenbinglife.common.base.exception.UncheckedException;

/**
 * 非法的请求参数异常
 *
 * @author shenbing
 * @version 2017/11/21
 * @since since
 */
public class InvalidParamsException extends UncheckedException implements ICodeException {

    private int code;

    public InvalidParamsException() {
    }

    public InvalidParamsException(String message) {
        super(message);
    }

    public InvalidParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParamsException(Throwable cause) {
        super(cause);
    }

    public InvalidParamsException(String message, int code) {
        super(message);
        this.code = code;
    }

    public InvalidParamsException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
