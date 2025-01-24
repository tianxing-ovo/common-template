package com.ltx.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author tianxing
 */
public interface ExportTaskService {

    List<LinkedHashMap<String, Object>> queryExportTaskList();
}
