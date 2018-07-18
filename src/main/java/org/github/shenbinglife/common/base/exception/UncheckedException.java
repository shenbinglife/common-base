package org.github.shenbinglife.common.base.exception;

/**
 * 自定义未受检异常
 *
 * @author SHEN
 * @version 2017/4/6
 * @since 1.0.0
 */
public class UncheckedException extends RuntimeException implements ICustomException {
    public UncheckedException() {
    }

    public UncheckedException(String message) {
        super(message);
    }

    public UncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncheckedException(Throwable cause) {
        super(cause);
    }
}
