package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "tab_exam_dept")
public class DeptDTO extends BaseDTO{
    // 部门名称
    private String deptName;

    // 上级部门
    private Integer parentId;
}
