package com.bes.item.service;

import com.bes.common.enums.ExceptionEnum;
import com.bes.common.exception.LeyouException;
import com.bes.item.mapper.SpecParamMapper;
import com.bes.item.mapper.SpecificationMapper;
import com.bes.item.pojo.SpecGroup;
import com.bes.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificatonService {
    @Autowired
    SpecificationMapper specificationMapper;

    @Autowired
    SpecParamMapper specParamMapper;

    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroups = specificationMapper.select(specGroup);
        if (CollectionUtils.isEmpty(specGroups)) {
            throw  new LeyouException(ExceptionEnum.SPECGROUP_NOT_FOUND_BY_ID);
        }

        return specGroups;

    }

    public List<SpecParam> queryyParamByGroupId(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> specParams = specParamMapper.select(specParam);
        if(CollectionUtils.isEmpty(specParams)) {
            throw new LeyouException(ExceptionEnum.SPECPARAM_NOT_FOUND_BY_GID);
        }

        return specParams;
    }

    public List<SpecParam> queryyParamList(Long gid, long cid, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);

        List<SpecParam> specParams = specParamMapper.select(specParam);
        if (CollectionUtils.isEmpty(specParams)) {
            throw new LeyouException(ExceptionEnum.SPECPARAM_NOT_FOUND_BY_GID);
        }
        return specParams;
    }

//    public List<SpecParam> queryParamByGroupId(Long gid) {
//        SpecParam specParam = new SpecParam();
//        specParam.setGroupId(gid);
//        System.out.println(gid);
//        System.out.println(specParam);
//        System.out.println(specParamMapper);
//        List<SpecParam> specParams = specParamMapper.select(specParam);
//        if(CollectionUtils.isEmpty(specParams)) {
//            throw new LeyouException(ExceptionEnum.SPECPARAM_NOT_FOUND_BY_GID);
//        }
//
//        return specParams;
//    }
}

