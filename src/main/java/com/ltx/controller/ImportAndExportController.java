package com.ltx.controller;

import com.alibaba.excel.EasyExcel;
import com.ltx.entity.User;
import com.ltx.listener.UserListener;
import common.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 导入和导出
 */
@RestController
@Slf4j
public class ImportAndExportController {

    /**
     * 使用poi库导入
     * HSSFWorkbook -> .xls
     * XSSFWorkbook -> .xlsx
     * SXSSFWorkbook/DeferredSXSSFWorkbook -> 大型.xlsx
     */
    @PostMapping("/importByPOI")
    public R importByPOI(@RequestParam("file") MultipartFile file) {
        List<User> UserList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter(); // 数据格式化
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream); // .xlsx
            // workbook.getNumberOfSheets()获取总页数
            Sheet sheet = workbook.getSheetAt(0); // 获取第一页
            int lastRowNum = sheet.getLastRowNum(); // 最后一行
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
    public R importByEasyExcel(@RequestParam("file") MultipartFile file) {
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
     * 使用easyExcel库导出
     */
    @GetMapping("/export/{fileName}")
    public void exportExample(HttpServletResponse response, @PathVariable("fileName") String fileName)
        throws IOException {
        response.setContentType("text/csv");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置头居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle,
            contentWriteCellStyle);
        // 构造导出数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "张三", 18));
        userList.add(new User(2, "李四", 19));
        userList.add(new User(3, "王五", 20));
        EasyExcel.write(response.getOutputStream(), User.class)
            .registerWriteHandler(horizontalCellStyleStrategy)
            .sheet(0, "学生表1")
            .doWrite(userList);
    }
}
