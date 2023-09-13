package com.ltx.easyExcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltx.easyExcel.ExcelConfig;
import com.ltx.entity.ExportRequestBody;
import exceptions.CustomException;
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
    ObjectMapper om;

    /**
     * 使用easyExcel库,导出CSV文件
     */
    public <T> void exportByEasyExcel(HttpServletResponse response, List<T> list, ExportRequestBody requestBody, Class<T> clazz) {
        String fileName = requestBody.getFileName();
        List<String> fields = requestBody.getFields();
        response.setContentType("text/csv;charset=UTF-8");
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            EasyExcel
                    .write(response.getOutputStream(), clazz)
                    .excelType(ExcelTypeEnum.CSV)
                    .registerWriteHandler(ExcelConfig.getCellWriteHandler())
                    .includeColumnFieldNames(fields)
                    .sheet()
                    .doWrite(list);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new CustomException(500, e.getMessage());
        }
    }
}

