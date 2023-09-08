package com.ltx.easyExcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.ltx.dao.UserDao;
import com.ltx.easyExcel.SexEnumConverter;
import com.ltx.entity.ExportRequestDTO;
import com.ltx.entity.User;
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
    UserDao userDao;

    @Resource
    HorizontalCellStyleStrategy styleStrategy;

    @Resource
    SexEnumConverter sexEnumConverter;

    /**
     * 使用easyExcel库,导出CSV文件
     */
    public void exportByEasyExcel(HttpServletResponse response, ExportRequestDTO requestDTO) {
        String fileName = requestDTO.getFileName();
        List<String> fields = requestDTO.getFields();
        List<User> userList = userDao.selectList(null);
        try {
            response.setContentType("text/csv;charset=UTF-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            EasyExcel.write(response.getOutputStream(), User.class)
                    .registerWriteHandler(styleStrategy)
                    .registerConverter(sexEnumConverter)
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
