package com.ltx.service.impl;

import com.ltx.mapper.ExportTaskMapper;
import com.ltx.service.ExportTaskService;
import com.ltx.util.UserContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author tianxing
 */
@Service
public class ExportTaskServiceImpl implements ExportTaskService {

    @Resource
    private ExportTaskMapper exportTaskMapper;

    @Override
    public List<LinkedHashMap<String, Object>> queryExportTaskList() {
        Integer id = UserContext.get().getId();
        return exportTaskMapper.queryExportTask(id);
    }
}
