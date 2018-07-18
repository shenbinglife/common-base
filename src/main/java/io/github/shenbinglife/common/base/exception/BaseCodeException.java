package io.github.shenbinglife.common.base.exception;

/**
 * 自定义带异常码的异常基类
 *
 * @author SHEN
 * @version 2017/4/6
 * @since 1.0.0
 */
public class BaseCodeException extends Exception implements ICodeException {
    private int code;

    public BaseCodeException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BaseCodeException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public BaseCodeException(int code) {
        this.code = code;
    }

    public BaseCodeException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
