package com.ltx.easyExcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ltx.constant.Constant;
import com.ltx.easyExcel.stylestrategy.CustomCellStyleStrategy;
import com.ltx.easyExcel.writehandler.CustomCellWriteHandler;
import com.ltx.entity.request.ExportRequestBody;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ExportService {

    /**
     * 导出文件到浏览器
     *
     * @param list        数据List
     * @param requestBody 请求体
     * @param clazz       数据类型
     */
    @SneakyThrows
    public <T> void export(HttpServletResponse response, List<T> list, ExportRequestBody requestBody, Class<T> clazz, ExcelTypeEnum excelType) {
        String fileName = requestBody.getFileName();
        List<String> fieldList = requestBody.getFieldList();
        response.setContentType("text/csv;charset=UTF-8");
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        EasyExcel.write(response.getOutputStream(), clazz)
                .excelType(excelType)
                .registerWriteHandler(new CustomCellStyleStrategy())
                .registerWriteHandler(new CustomCellWriteHandler())
                .includeColumnFieldNames(fieldList)
                .sheet()
                .doWrite(list);
    }

    /**
     * 导出文件到本地
     *
     * @param list        数据List
     * @param requestBody 请求体
     * @param clazz       数据类型
     * @param excelType   excel类型
     */
    public <T> void exportToLocal(List<T> list, ExportRequestBody requestBody, Class<T> clazz, ExcelTypeEnum excelType) {
        String fileName = requestBody.getFileName();
        List<String> fieldList = requestBody.getFieldList();
        EasyExcel.write(Paths.get(Constant.BASE_PATH, fileName).toFile(), clazz)
                .excelType(excelType)
                .registerWriteHandler(new CustomCellStyleStrategy())
                .registerWriteHandler(new CustomCellWriteHandler())
                .includeColumnFieldNames(fieldList)
                .sheet()
                .doWrite(list);
    }
}

