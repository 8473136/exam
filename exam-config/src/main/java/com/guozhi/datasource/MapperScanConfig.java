package com.guozhi.datasource;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * mapper扫描
 * @author LiuchangLan
 * @date 2020/7/12 19:42
 */
@Configuration
@MapperScan("com.guozhi.mapper")
public class MapperScanConfig {
}
