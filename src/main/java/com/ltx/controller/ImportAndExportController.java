package com.ltx.controller;

import com.ltx.dao.UserDao;
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


    /**
     * 使用poi库,导入xlsx文件
     */
    @PostMapping("/importByPOI")
    public R importByPOI(@RequestPart("file") MultipartFile file) {
        List<User> userList = importService.importByPOI(file);
        return R.ok("导入成功").put("userList", userList);
    }

    /**
     * 使用easyExcel库导入
     */
    @PostMapping("/importByEasyExcel")
    public R importByEasyExcel(@RequestPart("file") MultipartFile file) {
        List<User> userList = importService.importByEasyExcel(file);
        return R.ok("导入成功").put("userList", userList);
    }

    /**
     * 使用easyExcel库,导出CSV文件
     */
    @GetMapping("/exportByEasyExcel")
    public void exportByEasyExcel(HttpServletResponse response, ExportRequestDTO requestDTO) {
        List<User> list = userDao.select();
        exportService.exportByEasyExcel(response, list, requestDTO, User.class);
    }
}
