package com.bes.item.service;

import com.bes.common.enums.ExceptionEnum;
import com.bes.common.exception.LeyouException;
import com.bes.item.mapper.CategoryMapper;
import com.bes.item.pojo.Category;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findCategorysByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categories = categoryMapper.select(category);
        if(CollectionUtils.isEmpty(categories)){
            throw  new LeyouException(ExceptionEnum.CATEGORYS_FIND_BY_PID);
        }
        return categories;
    }

    // 根据id的list集合返回分类集合
    public List<Category> queryCategorysByIds(List<Long> ids) {
        List<Category> categories = categoryMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(categories)) {
            throw  new LeyouException(ExceptionEnum.CATEGORYS_FIND_BY_PID);
        }
        return categories;
    }
}
