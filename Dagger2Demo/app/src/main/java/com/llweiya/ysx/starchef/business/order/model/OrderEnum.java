package com.llweiya.ysx.starchef.business.order.model;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface OrderEnum {
    /**
     * 待支付
     */
    public String ORDER_STATUS_WAIT_PAY = "order_status_wait_pay";
    /**
     * 待评价
     */
    public String ORDER_STATUS_WAIT_COMMENT= "order_status_wait_comment";
    /**
     * 评价完成
     */
    public String ORDER_STATUS_FINISH = "order_status_finish";

    /**
     * 订单准备中
     */
    public String ORDER_STATUS_PREPARE = "order_status_prepare";
    @StringDef({ORDER_STATUS_WAIT_PAY, ORDER_STATUS_WAIT_COMMENT, ORDER_STATUS_FINISH})
    @Retention(RetentionPolicy.SOURCE)
    @interface OrderStatus{

    }
}
