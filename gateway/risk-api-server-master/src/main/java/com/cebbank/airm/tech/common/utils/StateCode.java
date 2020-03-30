package com.cebbank.airm.tech.common.utils;

import java.io.Serializable;

public enum StateCode implements Serializable {
    SUCCESS				("00000","响应成功")
    ,ER_PARAM_NOT_ACCESS("ER008","参数校验不通过")
    ,ER_USER_NOT_EX		("ER001","数据不存在")
    ,ER_U_NAME_PWD		("ER002","用户名或密码错误")
    ,ER_LOG_EXCEPTION	("ER003","服务名称已存在")
    ,ER_SESSION_TIMEOUT	("ER004","Session超时")
    ,ER_REFUSED			("ER005","无访问权限")
    ,ER_PARAM			("ER006","参数错误")
    ,ER_PASS_TIMES		("ER007","超过允许的访问次数")
    ,ER_INVALID_TOKEN	("ER008","无效的token")
    ,ER_SYSTEM_BUSY		("ER009","操作频繁,请稍后重试")
    ,ER_SERVER_ERROR	("ER010","服务不健康")
    ,ER_DATA_EXISTS		("ER011","数据已存在")
    ,ER_TRADE_NONE		("ER012","服务不存在")
    ,ER_TRADE_ABANDONED	("ER013","交易无权限访问")
    ,ER_GRANT_TYPE	    ("ER015","类型不存在")
    ,ER016	    		("ER016","交易未上线")
    ,ER_INNER_DATA("ER017","内部数据处理异常")
    ,ER_OUT_TER_DATA("ER018","外部数据处理异常")
    ,ER_MERGE_DATA("ER019","数据整合异常")
    ,ER_RUN_DECISION("ER020","调用规则引擎异常")
    ,ER_BACK_DATA("ER021","绑定决策结果输出异常")
    ,ER_REPEAT("ER022","重复的服务名称")
    ,ER_UN_KNOW			("ER999","未知错误")
    ,ERROR("11111","响应失败")
    ,FRAUD("ER023","信息有欺诈嫌疑")
    ,ER_GRADING("ER024","未通过评分卡")
    ,ER_LIMIT_MODEL("ER024","未通过评分卡")
    ,ER_GROUP_CALL("ER025","当前模型已被组合调用，不可下线")
    ,ER_DECISION_CALL("ER026","当前模型已被决策调用，不可下线")
    ;

    private String code;
    private String desc;

    StateCode() {
    }

    StateCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

