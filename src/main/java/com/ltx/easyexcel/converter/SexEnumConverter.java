package com.ltx.easyexcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.ltx.enums.SexEnum;

/**
 * 性别枚举转换器
 *
 * @author tianxing
 */
public class SexEnumConverter implements Converter<SexEnum> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return SexEnum.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 转换为Java数据
     */
    @Override
    public SexEnum convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String desc = String.valueOf(cellData.getData());
        return SexEnum.getEnumByDesc(desc);
    }

    /**
     * 转换为Excel数据
     */
    @Override
    public WriteCellData<?> convertToExcelData(SexEnum sexEnum, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        int value = sexEnum.getValue();
        return new WriteCellData<>(SexEnum.getDescByValue(value));
    }
}
