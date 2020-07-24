package com.guozhi.mapper;

import com.guozhi.dto.LogDTO;
import com.guozhi.rvo.LogRVO;
import com.guozhi.vo.LogVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/23 15:15
 */
public interface LogMapper extends Mapper<LogDTO> {

    List<LogRVO> getLogsByTime(LogVO logVO);

}
