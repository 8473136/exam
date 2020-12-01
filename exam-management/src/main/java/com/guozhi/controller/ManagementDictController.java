package com.guozhi.controller;

import com.guozhi.core.TraceLog;
import com.guozhi.dto.DictDTO;
import com.guozhi.service.DictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/13 14:54
 */
@RestController
@RequestMapping("management/dict")
public class ManagementDictController {

    @Resource
    private DictService dictService;

    @GetMapping("getAllDicts")
    @TraceLog(module = "字典管理",business = "获取所有字典")
    public List<DictDTO> getAllDicts(){
        return dictService.getAllDicts();
    }

    @PostMapping("addDicts")
    @TraceLog(module = "字典管理",business = "新增字典")
    public Integer addDicts(@RequestBody DictDTO dictDTO){
        return dictService.addDicts(dictDTO);
    }

    @DeleteMapping("deleteDicts")
    @TraceLog(module = "字典管理",business = "删除字典")
    public Integer deleteDicts(Integer id){
        return dictService.deleteDicts(id);
    }

    @GetMapping("getDictByParent")
    @TraceLog(module = "字典管理",business = "获取对应的子节点")
    public List<DictDTO> getDictByParent(String parentDictCode){
        return dictService.getDictByParent(parentDictCode);
    }

    @GetMapping("getDictById")
    @TraceLog(module = "字典管理",business = "根据字典id获取字典信息")
    public DictDTO getDictById(String id){
        return dictService.getDictById(id);
    }

    @PostMapping("updateDict")
    @TraceLog(module = "字典管理",business = "编辑字典")
    public Integer updateDict(@RequestBody DictDTO dictDTO){
        return dictService.updateDict(dictDTO);
    }
}
