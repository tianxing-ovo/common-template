package com.ltx.controller;

import com.ltx.easyExcel.service.ExportService;
import com.ltx.easyExcel.service.ImportService;
import com.ltx.entity.ExportRequestDTO;
import com.ltx.entity.User;
import common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 导入和导出
 */
@RestController
@Slf4j
public class ImportAndExportController {

    @Resource
    ExportService exportService;

    @Resource
    ImportService importService;


    /**
     * 使用poi库导入
     * HSSFWorkbook -> .xls
     * XSSFWorkbook -> .xlsx
     * SXSSFWorkbook/DeferredSXSSFWorkbook -> 大型.xlsx
     */
    @PostMapping("/importByPOI")
    public R importByPOI(@RequestPart("file") MultipartFile file) {
        List<User> userList;
        try {
            userList = importService.importByPOI(file);
        } catch (IOException e) {
            log.error(e.getMessage());
            return R.error(206, e.getMessage());
        }
        return R.ok("导入成功").put("userList", userList);
    }

    /**
     * 使用easyExcel库导入
     */
    @PostMapping("/importByEasyExcel")
    public R importByEasyExcel(@RequestPart("file") MultipartFile file) {
        List<User> userList;
        try {
            userList = importService.importByEasyExcel(file);
        } catch (IOException e) {
            log.error(e.getMessage());
            return R.error(206, e.getMessage());
        }
        return R.ok("导入成功").put("userList", userList);
    }

    /**
     * 使用POI库,导出CSV文件
     */
    @GetMapping("/exportByPOI")
    public void exportByPOI(HttpServletResponse response, ExportRequestDTO requestDTO) throws UnsupportedEncodingException {
        String fileName = requestDTO.getFileName();
        List<String> fields = requestDTO.getFields();
        response.setContentType("text/csv;charset=UTF-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

    }

    /**
     * 使用easyExcel库,导出CSV文件
     */
    @GetMapping("/exportByEasyExcel")
    public void exportByEasyExcel(HttpServletResponse response, ExportRequestDTO requestDTO) {
        exportService.exportByEasyExcel(response, requestDTO);
    }
}
