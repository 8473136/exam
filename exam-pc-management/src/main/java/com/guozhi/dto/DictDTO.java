package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 字典dto
 * @author LiuchangLan
 * @date 2020/7/13 11:31
 */
@Data
@Table(name = "tab_sys_dict")
public class DictDTO extends BaseDTO{
    // 字典名称
    private String dictName;

    // 父级id
    private Integer parentId;
}
