package cn.paopao.zuchebackend.service.alipay;

import cn.paopao.zuchebackend.config.AliPayConfig;
import cn.paopao.zuchebackend.entity.Order;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AliPayService {

    @Autowired
    AliPayConfig aliPayConfig;

    public String nativePay(Order order){
        AlipayTradePagePayResponse pay = null;
        try {
            pay = Factory.Payment.Page().pay(order.getCar().getName(), String.valueOf(order.getSerialNumber()), String.valueOf(order.getRelPay()), aliPayConfig.getReturnUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pay.getBody();
    }

    public String qrCodePay(Order order){
        AlipayTradePrecreateResponse response = null;
        AlipayClient client = new DefaultAlipayClient(aliPayConfig.getGateway()
                , aliPayConfig.getAppId()
                , aliPayConfig.getAppPrivateKey()
                , aliPayConfig.getFormat()
                , aliPayConfig.getCharset()
                , aliPayConfig.getAppPublicKey()
                , "RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        request.setBizContent("{"   +
                        "\"out_trade_no\":\""+order.getSerialNumber()+"\"," + // 商户订单号
                        "\"total_amount\":\""+order.getRelPay()+"\"," +	// 商品价格
                        "\"subject\":\""+order.getCar().getName()+"\"," +	// 商品标题
                        "\"timeout_express\":\"90m\"}");
        try {
            response = client.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response.getQrCode();
    }
}
