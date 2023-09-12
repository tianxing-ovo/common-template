package com.ltx.easyExcel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.ltx.mybatisPlus.SexEnum;

/**
 * 自定义转换器
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

    @Override
    public SexEnum convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String desc = String.valueOf(cellData.getData());
        if (desc.equals("男")) {
            return SexEnum.MAN;
        } else if (desc.equals("女")) {
            return SexEnum.WOMAN;
        } else {
            return SexEnum.UNKNOWN;
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(SexEnum value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        int sex = value.getValue();
        if (sex == 1) {
            return new WriteCellData<>("男");
        } else if (sex == 2) {
            return new WriteCellData<>("女");
        } else {
            return new WriteCellData<>("未知");
        }
    }

}
