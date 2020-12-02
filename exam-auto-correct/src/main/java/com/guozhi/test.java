package com.guozhi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiuchangLan
 * @date 2020/11/19 10:51
 */
public class test {

    public static void main(String[] args) {
        List<Object> a = new ArrayList<>();
        a.add(1);


        List<Integer> collect = a.stream().map(item -> Integer.parseInt(String.valueOf(item))).collect(Collectors.toList());
        System.out.println(collect.getClass());
    }
}
