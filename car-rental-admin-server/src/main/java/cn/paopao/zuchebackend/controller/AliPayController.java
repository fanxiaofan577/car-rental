package cn.paopao.zuchebackend.controller;

import cn.paopao.zuchebackend.entity.Car;
import cn.paopao.zuchebackend.entity.Order;
import cn.paopao.zuchebackend.mapper.CarMapper;
import cn.paopao.zuchebackend.mapper.OrderMapper;
import cn.paopao.zuchebackend.service.OrderService;
import cn.paopao.zuchebackend.service.WebSocketService;
import cn.paopao.zuchebackend.service.alipay.AliPayService;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.easysdk.factory.Factory;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    WebSocketService webSocketService;

    @PostMapping("/order")
    public String pay(int id){
        Order order = orderMapper.queryById(id);
        Car car = carMapper.queryById(order.getCid());
        order.setCar(car);
        String s = aliPayService.nativePay(order);
        return s;
    }

    @PostMapping("/qrCodePay")
    public String qrCodePay(int id){
        Order order = orderMapper.queryById(id);
        Car car = carMapper.queryById(order.getCid());
        order.setCar(car);

        String s = aliPayService.qrCodePay(order);
        return s;
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public void payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                orderMapper.updateStatus(1,params.get("out_trade_no"));
            }
            webSocketService.sendMessage("true");
        }
    }

}
