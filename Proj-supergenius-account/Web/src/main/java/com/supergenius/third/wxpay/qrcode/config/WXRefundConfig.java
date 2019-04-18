package com.supergenius.third.wxpay.qrcode.config;

import com.github.wxpay.sdk.WXPayConfig;
import java.io.*;

public class WXRefundConfig implements WXPayConfig{

    private byte[] certData;

    public WXRefundConfig() throws Exception {
        String certPath = WXConfig.certPath;
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return WXConfig.AppId;
    }

    public String getMchID() {
        return WXConfig.MchId;
    }

    public String getKey() {
        return WXConfig.ApiKey;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
