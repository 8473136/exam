package com.guozhi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/11/19 10:51
 */
public class test {

    public static void main(String[] args) {
        List<String> answers = new ArrayList<>();
        answers.add("2134");
        answers.add("1");
        answers.add("34");
        answers.add("21");
        Collections.sort(answers);
        System.out.println(answers);
    }
}
