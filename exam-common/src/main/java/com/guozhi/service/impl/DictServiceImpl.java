package com.guozhi.service.impl;

import com.guozhi.common.DataGlobalVariable;
import com.guozhi.dto.DictDTO;
import com.guozhi.mapper.DictMapper;
import com.guozhi.service.DictService;
import com.guozhi.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/13 14:53
 */
@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictMapper dictMapper;

    /**
     * @description 获取所有字典
     * @author LiuChangLan
     * @since 2020/7/14 14:48
     */
    @Override
    public List<DictDTO> getAllDicts() {
        DictDTO dictDTO = new DictDTO();
        dictDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        return dictMapper.select(dictDTO);
    }

    /**
     * @description 新增字典
     * @author LiuChangLan
     * @since 2020/7/14 14:49
     */
    @Override
    public Integer addDicts(DictDTO dictDTO) {
        return dictMapper.insertSelective(dictDTO);
    }


    /**
     * @description 删除字典
     * @author LiuChangLan
     * @since 2020/7/14 14:49
     */
    @Override
    public Integer deleteDicts(Integer id) {
        DictDTO dictDTO = dictMapper.selectByPrimaryKey(id);
        dictDTO.setIsDeleted(DataGlobalVariable.IS_DELETE);
        return dictMapper.updateByPrimaryKeySelective(dictDTO);
    }

    /**
     * @description 根据父节点获取子节点
     * @author LiuChangLan
     * @since 2020/7/14 14:49
     */
    @Override
    public List<DictDTO> getDictByParent(String parentDictCode) {
        DictDTO dictDTO = new DictDTO();
        dictDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        dictDTO.setDictCode(parentDictCode);
        DictDTO dictDTO1 = dictMapper.selectOne(dictDTO);
        dictDTO.setParentId(dictDTO1.getId());
        dictDTO.setDictCode(null);
        return dictMapper.select(dictDTO);
    }

    /**
     * @description 根据id获取信息
     * @author LiuChangLan
     * @since 2020/7/15 15:49
     */
    @Override
    public DictDTO getDictById(String id) {
        return dictMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateDict(DictDTO dictDTO) {
        dictDTO.setUpdateTime(DateUtils.currentDateTime());
        return dictMapper.updateByPrimaryKeySelective(dictDTO);
    }
}
