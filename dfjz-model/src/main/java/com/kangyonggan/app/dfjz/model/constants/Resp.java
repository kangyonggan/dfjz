package com.kangyonggan.app.dfjz.model.constants;

import lombok.Getter;

/**
 * 响应码枚举
 *
 * @author kangyonggan
 * @since 7/6/17
 */
public enum Resp {

    K0000("0000", "成功"),
    K0001("0001", "输入的数据不合法"),
    K9999("9999", "通用错误，请见具体错误描述");

    @Getter
    private final String respCode;

    @Getter
    private final String respMsg;

    private Resp(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

}
