package org.github.shenbinglife.common.base.exception;

/**
 * 建议将IOException转为UncheckIOException异常
 *
 * @author shenbing
 * @version 2017/12/29
 * @since since
 */
public class UncheckedIOException extends UncheckedException {

    public UncheckedIOException() {
    }

    public UncheckedIOException(String message) {
        super(message);
    }

    public UncheckedIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncheckedIOException(Throwable cause) {
        super(cause);
    }
}
