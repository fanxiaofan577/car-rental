package cn.paopao.zuchebackend.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {

    private String appId;
    private String appPrivateKey;
    private String appPublicKey;
    private String notifyUrl;
    private String returnUrl;
    private String gateway;
    private String format;
    private String charset;

    @PostConstruct
    public void init(){
        Config options = getOptions();
        options.appId=this.appId;
        options.merchantPrivateKey=this.appPrivateKey;
        options.alipayPublicKey = this.appPublicKey;
        options.notifyUrl = this.notifyUrl;

        Factory.setOptions(options);
    }

    private Config getOptions(){
        Config config = new Config();
        config.protocol="https";
        config.gatewayHost="openapi.alipaydev.com";
        config.signType = "RSA2";
        return config;
    }
}
