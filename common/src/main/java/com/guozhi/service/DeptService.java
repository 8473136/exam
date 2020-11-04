package com.guozhi.service;

import cn.hutool.core.lang.tree.Tree;
import com.guozhi.dto.DeptDTO;

import java.util.List;

public interface DeptService {
    List<Tree<String>> getDeptMenu();

    List<DeptDTO> getAllDept();

    DeptDTO getDeptById(Integer id);

    Integer addDept(DeptDTO deptDTO);

    Integer updateDept(DeptDTO deptDTO);

    Integer delDeps(Integer id);
}
