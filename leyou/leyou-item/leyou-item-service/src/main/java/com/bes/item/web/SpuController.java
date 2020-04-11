package com.bes.item.web;

import com.bes.common.vo.PageResult;
import com.bes.item.pojo.Spu;
import com.bes.item.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spu")
public class SpuController {
    @Autowired
    SpuService spuService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    ) {
        return ResponseEntity.ok(spuService.querySpuByPage(page, rows, saleable, key));
    }
}
