package com.guozhi.controller;

import com.guozhi.core.TraceLog;
import com.guozhi.dto.DictDTO;
import com.guozhi.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.RouterFunctionDslKt;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/13 14:54
 */
@RestController
@RequestMapping("dict")
public class DictController {

    @Resource
    private DictService dictService;

    @GetMapping("getAllDicts")
    @TraceLog(module = "字典管理",business = "获取所有字典")
    public List<DictDTO> getAllDicts(){
        return dictService.getAllDicts();
    }

    @PostMapping("addDicts")
    @TraceLog(module = "字典管理",business = "新增字典")
    public Integer addDicts(DictDTO dictDTO){
        return dictService.addDicts(dictDTO);
    }

    @DeleteMapping("deleteDicts")
    @TraceLog(module = "字典管理",business = "删除字典")
    public Integer deleteDicts(Integer id){
        return dictService.deleteDicts(id);
    }

    @GetMapping("getDictByParent")
    @TraceLog(module = "字典管理",business = "获取对应的子节点")
    public List<DictDTO> getDictByParent(Integer parentId){
        return dictService.getDictByParent(parentId);
    }

    @GetMapping("getDictById")
    @TraceLog(module = "字典管理",business = "根据字典id获取字典信息")
    public DictDTO getDictById(String id){
        return dictService.getDictById(id);
    }

    @PostMapping("updateDict")
    @TraceLog(module = "字典管理",business = "编辑字典")
    public Integer updateDict(DictDTO dictDTO){
        return dictService.updateDict(dictDTO);
    }
}
