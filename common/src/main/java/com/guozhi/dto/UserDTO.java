package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author LiuchangLan
 * @date 2020/11/4 10:44
 */
@Data
@Table(name = "tab_exam_user")
public class UserDTO extends BaseDTO{
    // 姓名
    private String name;
}
