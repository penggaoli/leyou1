package com.bes.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    CATEGORYS_FIND_BY_PID(404,"未查询到商品分类！"),
    PRICE_NOT_BE_NULL(400, "价格不能为空！"),
    BRAND_NOT_FOUND(404, "未查询到品牌！"),
    BRAND_SAVE_FIELD(500, "新增品牌失败！"),
    IMAGE_UPLOAD_FIELD(500,"图片上传失败！"),
    IMAGE_TYPE_NOT_ALLOW(400, "图片格式不合法！"),
    SPECGROUP_NOT_FOUND_BY_ID(404, "商品规格组没查到！"),
    SPECPARAM_NOT_FOUND_BY_GID(404, "商品参数没有查到！"),
    SPU_NOT_FOUND_BY_PAGE(404, "商品不存在！");
    ;
    private Integer code;
    private String message;
}
