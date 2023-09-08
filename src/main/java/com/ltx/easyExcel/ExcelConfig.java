package com.ltx.easyExcel;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class ExcelConfig {

    /**
     * 设置easyExcel对齐方式,居中对齐
     */
    @Bean
    public HorizontalCellStyleStrategy getStyleStrategy() {
        // 设置表头水平居中
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置内容水平居中
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    /**
     * 创建转换器
     */
    @Bean
    public SexEnumConverter sexEnumConverter() {
        return new SexEnumConverter();
    }
}
