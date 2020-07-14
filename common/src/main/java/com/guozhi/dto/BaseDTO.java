package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * DTO父类
 * @author LiuchangLan
 * @date 2020/6/27 23:02
 */
@Data
public class BaseDTO {
    /**
     * 主键id
     */
    @Id
    private Integer id;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private String updateTime;
    /**
     * 删除标识 0 正常 1删除
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;
    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createdBy;
    /**
     * 创建时间
     */
    @Column(name = "update_by")
    private String updatedBy;
}
