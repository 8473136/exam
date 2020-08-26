package com.guozhi.rvo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/8/25 9:52
 */
@Data
public class InitialHomeRVO {

    private HashMap<String,Object> homeInfo = new HashMap<>();

    private HashMap<String,Object> logoInfo = new HashMap<>();

    private List<RoleMenuRVO> menuInfo;

}
