package com.guozhi.vo;

import lombok.Data;

/**
 * @author LiuchangLan
 * @date 2020/7/21 16:17
 */
@Data
public class QuestionVO {

    // 题目名称
    private String questionName;

    // 题目类型 0单选 1多选 2判断  3填空 4应答题 5为案例分析题
    private Integer questionType;

    // 试题出处
    private String questionSource;

    // 题目所属库 0为考试库 1为练习库
    private Integer questionDepot;

    // 所属专题id
    private Integer specialId;

    private String options;

    private String answerOption;
}
