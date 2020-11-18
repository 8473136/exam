package com.guozhi.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * DTO生成工具类
 * @author LiuchangLan
 * @date 2020/6/28 10:32
 */
public class TableUtils {


    private static final String URL = "jdbc:mysql://115.159.38.225:3306/exam?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PASSWORD = "abcd123456!@#";

    static {
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        List<String> cloumns = new ArrayList<String>(){{
            add("create_by");
            add("update_by");
            add("create_time");
            add("update_time");
            add("is_deleted");
            add("id");
        }};
        Connection connection = DriverManager.getConnection(URL, USER , PASSWORD);
        Scanner scanner = new Scanner(System.in);
        String tableName = scanner("表名");
        String tableSql = "select COLUMN_NAME,column_comment,data_type  from INFORMATION_SCHEMA.Columns where table_name='" + tableName + "'  ";
        PreparedStatement ps = connection.prepareStatement(tableSql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            String clumn = resultSet.getString("COLUMN_NAME");
            if(cloumns.indexOf(clumn) != -1){
                continue;
            }
            String comment = resultSet.getString("column_comment");
            String type = "";
            switch (resultSet.getString("data_type")){
                case "varchar":
                    type = "String";
                    break;
                case "datetime":
                    type = "String";
                    break;
                case "int":
                    type = "Integer";
                    break;
            }
            int index = clumn.indexOf("_");
            while (index != -1){
                clumn = clumn.substring(0,index) + clumn.substring(index + 1,index + 2).toUpperCase() + clumn.substring(index + 2);
                index = clumn.indexOf("_");
            }
            System.out.println("// " + comment);
            System.out.println("private " + type + " " + clumn + ";");
            System.out.println();
        }
    }



    /**
     * 读取控制台输入
     * @author WangJixin
     * @since 2019/9/24 11:53
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            return scanner.next();
        }
        throw new RuntimeException("输入有误");
    }

}
