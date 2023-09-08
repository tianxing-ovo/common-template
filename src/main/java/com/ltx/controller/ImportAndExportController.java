package com.ltx.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ltx.config.StyleConfig;
import com.ltx.entity.ExportRequestDTO;
import com.ltx.entity.User;
import com.ltx.listener.UserListener;
import common.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 导入和导出
 */
@RestController
@Slf4j
public class ImportAndExportController {

    private final List<User> userList = Arrays.asList(
            new User(1, "张三", "123456"),
            new User(2, "李四", "654321"),
            new User(3, "王五", "987654")
    );

    /**
     * 使用poi库导入
     * HSSFWorkbook -> .xls
     * XSSFWorkbook -> .xlsx
     * SXSSFWorkbook/DeferredSXSSFWorkbook -> 大型.xlsx
     */
    @PostMapping("/importByPOI")
    public R importByPOI(@RequestPart("file") MultipartFile file) {
        List<User> UserList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter(); // 数据格式化
        try {
            InputStream inputStream = file.getInputStream();
            // 获取工作簿
            Workbook workbook = new XSSFWorkbook(inputStream);
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 最后一行
            int lastRowNum = sheet.getLastRowNum();
            // 跳过第一行(表头),rowNum=0
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    User user = new User();
                    user.setId((int) row.getCell(0).getNumericCellValue());
                    user.setName(row.getCell(1).getStringCellValue());
                    user.setPassword(dataFormatter.formatCellValue(row.getCell(2))); // 数字 -> 字符串
                    UserList.add(user);
                }
            }
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            return R.error(206, e.getMessage());
        }
        return R.ok("导入成功").put("userList", UserList);
    }

    /**
     * 使用easyExcel库导入
     */
    @PostMapping("/importByEasyExcel")
    public R importByEasyExcel(@RequestPart("file") MultipartFile file) {
        UserListener userListener = new UserListener();
        try {
            InputStream inputStream = file.getInputStream();
            // 默认Excel的第一行是头信息(表头) sheet从0开始 sheet()默认为第一页 doReadAll()读取全部数据
            EasyExcel.read(inputStream, User.class, userListener).sheet().doRead();
        } catch (IOException e) {
            log.error(e.getMessage());
            return R.error(206, e.getMessage());
        }
        return R.ok("导入成功").put("userList", userListener.getUserList());
    }

    /**
     * 使用POI库,导出CSV文件
     */
    @GetMapping("/exportByPOI")
    public void exportByPOI(HttpServletResponse response, ExportRequestDTO requestDTO) throws IOException {
        String fileName = requestDTO.getFileName();
        List<String> fields = requestDTO.getFields();
        response.setContentType("text/csv");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

    }

    /**
     * 使用easyExcel库,导出CSV文件
     */
    @GetMapping("/exportByEasyExcel")
    public void exportByEasyExcel(HttpServletResponse response, ExportRequestDTO requestDTO) {
        String fileName = requestDTO.getFileName();
        List<String> fields = requestDTO.getFields();
        try {
            response.setContentType("text/csv;charset=UTF-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            EasyExcel.write(response.getOutputStream(), User.class)
                    .registerWriteHandler(StyleConfig.getStyleStrategy())
                    .excelType(ExcelTypeEnum.CSV)
                    .includeColumnFieldNames(fields) // 动态表头
                    .sheet()
                    .doWrite(userList);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            log.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
