package com.bes.item.web;

import com.bes.common.vo.PageResult;
import com.bes.item.pojo.Brand;
import com.bes.item.service.BrandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ) {
        return ResponseEntity.ok(brandService.queryBrandByPage(page,rows,sortBy,desc,key));
    }

    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand newBrand, @RequestParam(value = "cids") List<Long> cids) {
        brandService.saveBrand(newBrand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(brandService.queryBrandsByCid(cid));
    }
}
