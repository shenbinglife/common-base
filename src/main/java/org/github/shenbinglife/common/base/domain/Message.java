package org.github.shenbinglife.common.base.domain;

/**
 * 消息体
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
public class Message<T> {

    private String message;

    private int code;

    private T data;

    public Message() {
    }

    public Message(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public Message(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
