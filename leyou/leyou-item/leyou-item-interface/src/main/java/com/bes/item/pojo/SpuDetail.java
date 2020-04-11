package com.bes.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    private Long spuId;              // 对应的SPU的id
    private String description;     // 商品的描述
    private String specialSpec;     // 商品特殊规格的名称及可选值模版
    private String genericSpec;     // 商品全规格属性
    private String packingList;     // 包装清单
    private String afterService;    // 售后服务
}
