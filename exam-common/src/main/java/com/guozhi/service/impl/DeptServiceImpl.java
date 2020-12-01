package com.guozhi.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.dto.DeptDTO;
import com.guozhi.mapper.DeptMapper;
import com.guozhi.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<Tree<String>> getDeptMenu() {
        // 查询未删除的dept记录
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);

        List<DeptDTO> deptLists = deptMapper.select(deptDTO);

        // 创建树型结构

        // 配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setNameKey("title");
        treeNodeConfig.setChildrenKey("children");
        treeNodeConfig.setDeep(1000);

        // 创建树
        List<Tree<String>> build = TreeUtil.build(deptLists, "-1", treeNodeConfig,
                (deptDTO1, tree) -> {
                    tree.setId(String.valueOf(deptDTO1.getId()));
                    tree.setName(String.valueOf(deptDTO1.getDeptName()));
                    tree.setParentId(String.valueOf(deptDTO1.getParentId()));
                });


        return build;
    }

    @Override
    public List<DeptDTO> getAllDept() {
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        return deptMapper.select(deptDTO);
    }

    @Override
    public DeptDTO getDeptById(Integer id) {
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setId(id);
        DeptDTO deptDTO1 = deptMapper.selectByPrimaryKey(deptDTO);
        return deptDTO1;
    }

    @Override
    public Integer addDept(DeptDTO deptDTO) {
        return deptMapper.insertSelective(deptDTO);
    }

    @Override
    public Integer updateDept(DeptDTO deptDTO) {
        deptDTO.setUpdateTime(DateUtil.now());
        return deptMapper.updateByPrimaryKeySelective(deptDTO);
    }

    @Override
    public Integer delDeps(Integer id) {
        DeptDTO deptDTO = deptMapper.selectByPrimaryKey(id);
        deptDTO.setIsDeleted(DataGlobalVariable.IS_DELETE);
        return deptMapper.updateByPrimaryKeySelective(deptDTO);
    }
}
