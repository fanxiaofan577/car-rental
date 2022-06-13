package cn.paopao.zuchebackend.service;


import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Category;
import cn.paopao.zuchebackend.entity.Order;
import cn.paopao.zuchebackend.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public PageBean queryAllByLimit(Category category, PageBean<Category> pageBean) {
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Category> categories = categoryMapper.queryAllByLimit(category);
        long count = categoryMapper.count(category);
        pageBean.setTotal(count);
        pageBean.setContent(categories);
        pageBean.setTotalPage(pageBean.getTotal()/ pageBean.getPageSize()+1);
        return pageBean;
    }
}
