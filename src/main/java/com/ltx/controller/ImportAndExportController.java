package com.ltx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltx.dao.UserDao;
import com.ltx.easyExcel.service.ExportService;
import com.ltx.easyExcel.service.ImportService;
import com.ltx.entity.ExportRequestDTO;
import com.ltx.entity.User;
import common.R;
import exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    UserDao userDao;

    @Resource
    ImportService importService;

    @Resource
    ObjectMapper om;


    /**
     * 使用poi库,导入xlsx文件
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
     * 使用easyExcel库,导出CSV文件
     */
    @GetMapping("/exportByEasyExcel")
    public void exportByEasyExcel(HttpServletResponse response, ExportRequestDTO requestDTO) {
        List<User> list = userDao.selectList(null);
        try {
            exportService.exportByEasyExcel(response, list, requestDTO, User.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new CustomException(500, e.getMessage());
        }
    }
}
