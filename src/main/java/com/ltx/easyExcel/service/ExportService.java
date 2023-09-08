package com.ltx.easyExcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ltx.dao.UserDao;
import com.ltx.entity.ExportRequestDTO;
import com.ltx.entity.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Slf4j
public class ExportService {
    @Resource
    UserDao userDao;

    @Resource
    HorizontalCellStyleStrategy styleStrategy;

    @Resource
    ThreadPoolExecutor executor;

    /**
     * 使用easyExcel库,导出CSV文件
     */
    @SneakyThrows
    public void exportByEasyExcel(HttpServletResponse response, ExportRequestDTO requestDTO) {
        String fileName = requestDTO.getFileName();
        List<String> fields = requestDTO.getFields();
        response.setContentType("text/csv;charset=UTF-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        int pageNum = 10;
        int size = 10000;
        CountDownLatch latch = new CountDownLatch(pageNum);
        List<List<User>> userList = new ArrayList<>();
        for (int i = 1; i <= pageNum; i++) {
            int num = i;
            executor.execute(() -> {
                Page<User> page = new Page<>(num, size);
                userList.add(userDao.selectPage(page, null).getRecords());
                latch.countDown();
            });
        }
        latch.await();
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), User.class).excelType(ExcelTypeEnum.CSV).includeColumnFieldNames(fields).registerWriteHandler(styleStrategy).build();
        for (int i = 0; i < userList.size(); i++) {
            WriteSheet writeSheet = EasyExcel.writerSheet(i).build();
            excelWriter.write(userList.get(i), writeSheet);
        }
        excelWriter.close();
    }
}

