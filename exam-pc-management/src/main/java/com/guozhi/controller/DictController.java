package com.guozhi.controller;

import com.guozhi.dto.DictDTO;
import com.guozhi.service.DictService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.*;

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
    public List<DictDTO> getAllDicts(){
        return dictService.getAllDicts();
    }

    @PostMapping("addDicts")
    public Integer addDicts(DictDTO dictDTO){
        return dictService.addDicts(dictDTO);
    }

    @DeleteMapping("deleteDicts")
    public Integer deleteDicts(Integer id){
        return dictService.deleteDicts(id);
    }

    @GetMapping("getDictByParent")
    public List<DictDTO> getDictByParent(Integer parentId){
        return dictService.getDictByParent(parentId);
    }
}
