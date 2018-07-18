package org.github.shenbinglife.common.base.exception;

/**
 * 带返回码的异常接口
 *
 * @author SHEN
 * @version 2017/4/6
 * @since 1.0.0
 */
public interface ICodeException extends ICustomException {

    int getCode();
}
