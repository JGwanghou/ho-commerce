package com.gwangho.commerce.app.infra.platform;

public interface OrderAfterDataPlatForm {
    void slackAlarm(Object obj);
    void kakaoAlarm(Object obj);
    void smsAlarm(Object obj);
}
