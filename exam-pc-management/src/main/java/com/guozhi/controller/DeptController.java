package com.guozhi.controller;

import cn.hutool.core.lang.tree.Tree;
import com.guozhi.dto.DeptDTO;
import com.guozhi.rvo.InitialHomeRVO;
import com.guozhi.service.DeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("getDeptMenu")
    public List<Tree<String>> getDeptMenu() {
        return deptService.getDeptMenu();
    }

    @GetMapping("getAllDept")
    public List<DeptDTO> getAllDept() {
        return deptService.getAllDept();
    }

    @GetMapping("getDeptById")
    public DeptDTO getDeptById(Integer id) {
        return deptService.getDeptById(id);
    }

    @PostMapping("addDept")
    public Integer addDept(@RequestBody DeptDTO deptDTO) {
        return deptService.addDept(deptDTO);
    }

    @PostMapping("updateDept")
    public Integer updateDept(@RequestBody DeptDTO deptDTO) {
        return deptService.updateDept(deptDTO);
    }


    @DeleteMapping("delDept")
    public Integer delDept(Integer id) {
        return deptService.delDeps(id);
    }


}
