package com.guozhi.vo;

import lombok.Data;

import java.util.List;

/**
 * 试卷添加题目VO
 * @author LiuchangLan
 * @date 2020/9/2 9:53
 */
@Data
public class PaperImportQuestionVO {

    // 试卷id
    private Integer id;

    // 总分
    private Double totalScore;

    // 合格分
    private Double passScore;

    // 固定题目id集合
    private List<Integer> fixedQuestion;

    //随机题目入参VO对象
    private List<PaperRandomQuestionVO> randomQuestion;

}
