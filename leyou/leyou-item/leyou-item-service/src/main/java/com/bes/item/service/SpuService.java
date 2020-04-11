package com.bes.item.service;

import com.bes.common.enums.ExceptionEnum;
import com.bes.common.exception.LeyouException;
import com.bes.common.vo.PageResult;
import com.bes.item.mapper.SpuDetailMapper;
import com.bes.item.mapper.SpuMapper;
import com.bes.item.pojo.Category;
import com.bes.item.pojo.Spu;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpuService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;


    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example spuExample = new Example(Spu.class);
        Example.Criteria spuExampleCriteria = spuExample.createCriteria();
        if (StringUtils.isNotBlank(key)) {
            spuExampleCriteria.andLike("title", "%" + key + "%");
        }

        if (null != saleable) {
            spuExampleCriteria.andEqualTo("saleable", saleable);
        }
        // 排序
        spuExample.setOrderByClause("id");
        // 判断是否查询到
        List<Spu> spus = spuMapper.selectByExample(spuExample);
        if (CollectionUtils.isEmpty(spus)) {
            throw new LeyouException(ExceptionEnum.SPU_NOT_FOUND_BY_PAGE);
        }

        // 解析分类和品牌的名称
        loadCategoryAndBrandName(spus);

        // 返回
        PageInfo<Spu> spuPageInfo = new PageInfo<>(spus);
        return new PageResult<Spu>(spuPageInfo.getTotal(), spus);
    }

    public void loadCategoryAndBrandName(List<Spu> spus) {
        // 处理分类名称
        for(Spu spu : spus) {
            List<String> names = categoryService.queryCategorysByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())).stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(names, "/"));
            spu.setBname(brandService.queryBrandById(spu.getBrandId()).getName());
        }


    }
}
