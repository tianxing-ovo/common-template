package com.ltx.easyexcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.List;

/**
 * List转换器
 *
 * @author tianxing
 */
public class ListConverter implements Converter<List<String>> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return List.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 转换为Excel数据
     */
    @Override
    public WriteCellData<?> convertToExcelData(List<String> list, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        if (list == null || list.isEmpty()) {
            return new WriteCellData<>("--");
        } else {
            return new WriteCellData<>(String.join(",", list));
        }
    }
}

