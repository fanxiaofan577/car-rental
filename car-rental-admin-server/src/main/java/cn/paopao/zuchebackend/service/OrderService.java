package cn.paopao.zuchebackend.service;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Order;
import cn.paopao.zuchebackend.entity.SystemResults;
import cn.paopao.zuchebackend.mapper.CarMapper;
import cn.paopao.zuchebackend.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CarMapper carMapper;


    /**
     * 返回订单总数
     * @return
     */
    public long total(Order order){
        return orderMapper.count(order);
    }


    public PageBean queryAllByLimit(Order order, PageBean<Order> pageBean) {
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Order> orders = orderMapper.queryAllByLimit(order);
        long count = orderMapper.count(order);
        pageBean.setTotal(count);
        pageBean.setContent(orders);
        pageBean.setTotalPage(pageBean.getTotal()/ pageBean.getPageSize()+1);
        return pageBean;
    }

    public SystemResults backCar(Order order){
        order.setStatus(2);
        int i = orderMapper.update(order);
        if (i != 0 ){
            return SystemResults.ok("还车成功！");
        }else {
            return SystemResults.ok("还车失败！");
        }
    }
}
