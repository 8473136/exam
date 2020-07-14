package com.guozhi.service.impl;

import com.guozhi.dto.DictDTO;
import com.guozhi.mapper.DictMapper;
import com.guozhi.service.DictService;
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

    @Override
    public List<DictDTO> getAllDicts() {
        DictDTO dictDTO = new DictDTO();
        dictDTO.setIsDeleted(0);
        return dictMapper.select(dictDTO);
    }

    @Override
    public Integer addDicts(DictDTO dictDTO) {
        return dictMapper.insertSelective(dictDTO);
    }

    @Override
    public Integer deleteDicts(Integer id) {
        DictDTO dictDTO = dictMapper.selectByPrimaryKey(id);
        dictDTO.setIsDeleted(1);
        return dictMapper.updateByPrimaryKeySelective(dictDTO);
    }
}
