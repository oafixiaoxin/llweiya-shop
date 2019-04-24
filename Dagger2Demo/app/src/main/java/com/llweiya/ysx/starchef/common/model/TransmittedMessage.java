package com.llweiya.ysx.starchef.common.model;

/**
 * Created by ysx on 2018/1/24.
 */

public class TransmittedMessage {

    public String appCode;
    public String message;
    public String messageAction;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageAction() {
        return messageAction;
    }

    public void setMessageAction(String messageAction) {
        this.messageAction = messageAction;
    }
}
