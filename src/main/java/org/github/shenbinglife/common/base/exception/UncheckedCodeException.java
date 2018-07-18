package org.github.shenbinglife.common.base.exception;

/**
 * 带错误码的未受检异常
 *
 * @author SHEN
 * @version 2017/4/6
 * @since 1.0.0
 */
public class UncheckedCodeException extends UncheckedException implements ICodeException {
    private int code;

    public UncheckedCodeException(String message, int code) {
        super(message);
        this.code = code;
    }

    public UncheckedCodeException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
