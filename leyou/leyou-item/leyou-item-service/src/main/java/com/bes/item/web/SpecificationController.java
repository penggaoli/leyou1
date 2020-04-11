package com.bes.item.web;

import com.bes.item.pojo.SpecGroup;
import com.bes.item.pojo.SpecParam;
import com.bes.item.service.SpecificatonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {
    @Autowired
    SpecificatonService specificatonService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(specificatonService.queryGroupByCid(cid));
    }

    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamsByGroupId(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) long cid,
            @RequestParam(value = "searching", required = false) Boolean searching) {
        return ResponseEntity.ok(specificatonService.queryyParamList(gid, cid, searching));
    }

}
