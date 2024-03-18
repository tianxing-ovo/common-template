package com.ltx.easyExcel.service;

import com.alibaba.excel.EasyExcel;
import com.ltx.entity.User;
import com.ltx.exception.CustomException;
import com.ltx.listener.UserListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ImportService {
    /**
     * 使用poi库导入
     * HSSFWorkbook -> .xls
     * XSSFWorkbook -> .xlsx
     * SXSSFWorkbook/DeferredSXSSFWorkbook -> 大型.xlsx
     */
    public List<User> importByPOI(MultipartFile file) {
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
                    //user.setPassword(dataFormatter.formatCellValue(row.getCell(2))); // 数字 -> 字符串
                    UserList.add(user);
                }
            }
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new CustomException(500, e.getMessage());
        }
        return UserList;
    }

    /**
     * 使用easyExcel库导入
     */
    public List<User> importByEasyExcel(MultipartFile file) {
        UserListener userListener = new UserListener();
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            // 默认Excel的第一行是头信息(表头) sheet从0开始 sheet()默认为第一页 doReadAll()读取全部数据
            EasyExcel.read(inputStream, User.class, userListener).sheet().doRead();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new CustomException(500, e.getMessage());
        }
        return userListener.getUserList();
    }
}
