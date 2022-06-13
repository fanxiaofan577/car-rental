package cn.paopao.zuchebackend.service;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Category;
import cn.paopao.zuchebackend.entity.RoughlyCar;
import cn.paopao.zuchebackend.mapper.RoughlyCarMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoughlyCarService {

    @Autowired
    RoughlyCarMapper roughlyCarMapper;

    public PageBean queryAllByLimit(RoughlyCar roughlyCar, PageBean<RoughlyCar> pageBean) {
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<RoughlyCar> roughlyCars = roughlyCarMapper.queryAllByLimit(roughlyCar);
        long count = roughlyCarMapper.count(roughlyCar);
        pageBean.setTotal(count);
        pageBean.setContent(roughlyCars);
        pageBean.setTotalPage(pageBean.getTotal()/ pageBean.getPageSize()+1);
        return pageBean;
    }
}
