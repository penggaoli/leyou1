package com.bes.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "tb_spu")
public class Spu {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long brandId;       // 品牌
    private Long cid1;          // 一级目录
    private Long cid2;          // 二级目录
    private Long cid3;          // 三级目录
    private String title;       // 标题
    private String subTitle;   // 副标题
    private Boolean saleable;  // 是否上架
    @JsonIgnore
    private Boolean valid;     // 是否有效
    private Date createTime;    // 创建时间
    @JsonIgnore
    private Date lastUpdateTime; // 最后一次更新时间

    @Transient
    private String cname;
    @Transient
    private String bname;

}
