package com.ltx.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.ltx.easyExcel.service.ExportService;
import com.ltx.easyExcel.service.ImportService;
import com.ltx.entity.User;
import com.ltx.entity.request.ExportRequestBody;
import com.ltx.mapper.UserMapper;
import io.github.tianxingovo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 导入和导出
 */
@RestController
@Slf4j
public class ImportAndExportController {

    @Resource
    private ExportService exportService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ImportService importService;

    @Resource
    private ThreadPoolExecutor executor;

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
     * 使用easyExcel库,导出CSV文件到浏览器
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody ExportRequestBody requestBody) {
        List<User> list = userMapper.select();
        exportService.export(response, list, requestBody, User.class, ExcelTypeEnum.CSV);
    }

    /**
     * 使用easyExcel库,异步导出CSV文件到本地
     */
    @PostMapping("/exportToLocal")
    public void asyncExport(@RequestBody ExportRequestBody requestBody) {
        executor.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<User> list = userMapper.select();
            exportService.exportToLocal(list, requestBody, User.class, ExcelTypeEnum.CSV);
        });
    }
}
