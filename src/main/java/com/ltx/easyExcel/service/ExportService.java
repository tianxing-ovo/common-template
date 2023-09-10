package com.ltx.easyExcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.ltx.entity.ExportRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
@Slf4j
public class ExportService {
    @Resource
    HorizontalCellStyleStrategy styleStrategy;

    /**
     * 使用easyExcel库,导出CSV文件
     */
    public <T> void exportByEasyExcel(HttpServletResponse response, List<T> list, ExportRequestDTO requestDTO, Class<T> clazz) throws IOException {
        String fileName = requestDTO.getFileName();
        List<String> fields = requestDTO.getFields();
        response.setContentType("text/csv;charset=UTF-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        EasyExcel.write(response.getOutputStream(), clazz).excelType(ExcelTypeEnum.CSV).includeColumnFieldNames(fields).registerWriteHandler(styleStrategy).sheet().doWrite(list);
    }
}

