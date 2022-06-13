package cn.paopao.zuchebackend.service;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Car;
import cn.paopao.zuchebackend.entity.Category;
import cn.paopao.zuchebackend.mapper.CarMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarMapper carMapper;

    /**
     * 返回用户总数
     * @return
     */
    public long total(Car car){
        return carMapper.count(car);
    }

    public PageBean queryAllByLimit(Car car, PageBean<Car> pageBean) {
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Car> categories = carMapper.queryAllByLimit(car);
        long count = carMapper.count(car);
        pageBean.setTotal(count);
        pageBean.setContent(categories);
        pageBean.setTotalPage(pageBean.getTotal()/ pageBean.getPageSize()+1);
        return pageBean;
    }
}
