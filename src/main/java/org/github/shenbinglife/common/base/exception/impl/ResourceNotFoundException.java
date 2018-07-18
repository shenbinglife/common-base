package org.github.shenbinglife.common.base.exception.impl;


import org.github.shenbinglife.common.base.exception.ICodeException;
import org.github.shenbinglife.common.base.exception.UncheckedException;

/**
 * 资源不存在异常
 *
 * @author shenbing
 * @version 2018/1/22
 * @since since
 */
public class ResourceNotFoundException extends UncheckedException implements ICodeException {

    private int code;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ResourceNotFoundException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
