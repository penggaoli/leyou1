package com.bes.item.service;

import com.bes.common.enums.ExceptionEnum;
import com.bes.common.exception.LeyouException;
import com.bes.common.vo.PageResult;
import com.bes.item.mapper.BrandMapper;
import com.bes.item.pojo.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example brandExample = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            brandExample.createCriteria().orLike("name", "%" + key + "%")
                    .orEqualTo("letter", key.toUpperCase());
        }
        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            brandExample.setOrderByClause(orderByClause);
        }
        // 查询
        List<Brand> brandList = brandMapper.selectByExample(brandExample);    // 总的数据数也会查询到
        if(CollectionUtils.isEmpty(brandList)) {
            throw new LeyouException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        // 解析分页结果
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brandList);

        return new PageResult<Brand>(brandPageInfo.getTotal(), brandList);
    }

    @Transactional
    public void saveBrand(Brand newBrand, List<Long> cids) {
        newBrand.setId(null);
        int isSave = brandMapper.insert(newBrand);   //新增成功后，会将id回设给newBrand
        if (isSave != 1) {
            throw new LeyouException(ExceptionEnum.BRAND_SAVE_FIELD);
        }
        for (Long cid : cids) {
            isSave = brandMapper.saveBrandCategory(newBrand.getId(), cid);
            if(isSave != 1) {
                throw new LeyouException(ExceptionEnum.BRAND_SAVE_FIELD);
            }
        }
    }

    public Brand queryBrandById(Long id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (null == brand) {
            throw new LeyouException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        return brand;
    }

    public List<Brand> queryBrandsByCid(Long cid) {
        List<Brand> brands = brandMapper.queryBrandsByCid(cid);
        if (CollectionUtils.isEmpty(brands)) {
            throw new LeyouException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        return brands;
    }
}
