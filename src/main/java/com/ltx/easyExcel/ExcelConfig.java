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
 * CellWriteHandler:
 * 作用范围: 单元格级别
 * 主要功能: 写入单元格时自定义单元格的样式和内容
 * 示例用途: 设置单元格的样式/自定义单元格的值/添加数据校验规则
 * <p>
 * RowWriteHandler:
 * 作用范围: 行级别
 * 主要功能: 允许您在写入行数据时自定义整行的样式和内容
 * 示例用途: 设置行的样式/合并单元格/动态调整行高
 * <p>
 * SheetWriteHandler:
 * 作用范围: 工作表级别
 * 主要功能: 自定义整个工作表的样式和内容
 * 示例用途: 设置工作表的名称/设置工作表的冻结窗格/添加工作表级别的数据校验
 * <p>
 * WorkbookWriteHandler:
 * 作用范围: 工作簿级别
 * 主要功能: 自定义整个工作簿的样式和内容
 * 示例用途: 设置工作簿级别的样式/添加自定义的元数据信息/设置全局的数据格式
 */
public class ExcelConfig {

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
