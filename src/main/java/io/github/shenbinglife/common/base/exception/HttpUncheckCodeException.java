package io.github.shenbinglife.common.base.exception;

/**
 * CLASS_NAME
 *
 * @author shenbing
 * @version 2017/11/16
 * @since since
 */
public class HttpUncheckCodeException extends UncheckedCodeException {

    private int httpStatus;

    public HttpUncheckCodeException(String message, int code, int httpStatus) {
        super(message, code);
        this.httpStatus = httpStatus;
    }

    public HttpUncheckCodeException(String message, Throwable cause, int code, int httpStaus) {
        super(message, cause, code);
        this.httpStatus = httpStaus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
