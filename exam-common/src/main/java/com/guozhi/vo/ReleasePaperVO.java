package com.guozhi.vo;

import lombok.Data;

import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/11/5 17:32
 */
@Data
public class ReleasePaperVO {
    // 试卷id
    private Integer id;
    // 发布的用户id
    private List<Integer> userIds;
}
