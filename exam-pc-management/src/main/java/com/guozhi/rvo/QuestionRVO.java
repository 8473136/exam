package com.guozhi.rvo;

import com.guozhi.dto.QuestionDTO;
import lombok.Data;

/**
 * 题目rvo
 * @author LiuchangLan
 * @date 2020/7/17 14:19
 */
@Data
public class QuestionRVO {

    private Integer id;

    private String createTime;

    // 题目名称
    private String questionName;

    // 题目类型 0单选 1多选 2判断  3填空 4应答题 5为案例分析题
    private String questionTypeName;

    // 父题 案例分析-父题id
    private Integer parentQuestionId;

    // 试题出处
    private String questionSource;

    // 题目所属库 0为考试库 1为练习库
    private String questionDepotName;

    // 所属专题
    private String specialIdName;

}
