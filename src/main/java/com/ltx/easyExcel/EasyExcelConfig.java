package com.ltx.easyExcel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.List;

/**
 * easyExcel配置
 */
public class EasyExcelConfig {

    /**
     * 设置easyExcel对齐方式,居中对齐
     */
    public static HorizontalCellStyleStrategy getStyleStrategy() {
        // 设置表头水平居中
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置内容水平居中
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    /**
     * 作用范围: 单元格级别
     * 主要功能: 写入单元格时自定义单元格的样式和内容
     * 示例用途: 设置单元格的样式/自定义单元格的值/添加数据校验规则
     * 处理null/空字符串
     */
    public static CellWriteHandler getCellWriteHandler() {
        return new CellWriteHandler() {
            @Override
            public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
                CellType cellType = cell.getCellType();
                if (cellType == CellType._NONE || (cellType == CellType.STRING && cell.getStringCellValue().equals(""))) {
                    cell.setCellValue("--");
                }
            }
        };
    }
}
