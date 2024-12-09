package com.ltx.entity.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页结果
 */
@Data
public class PageDTO {

    // 总记录数
    private Long total;
    // 总页数
    private Long pages;
    // 当前页的数据列表
    private List<?> list;

    public static PageDTO of(Page<?> page, Class<?> targetType) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotal(page.getTotal());
        pageDTO.setPages(page.getPages());
        List<?> records = page.getRecords();
        if (CollUtil.isEmpty(records)) {
            pageDTO.setList(Collections.emptyList());
            return pageDTO;
        }
        pageDTO.setList(BeanUtil.copyToList(records, targetType));
        return pageDTO;
    }

    public static <PO, VO> PageDTO of(Page<PO> page, Function<PO, VO> function) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotal(page.getTotal());
        pageDTO.setPages(page.getPages());
        List<PO> records = page.getRecords();
        if (CollUtil.isEmpty(records)) {
            pageDTO.setList(Collections.emptyList());
            return pageDTO;
        }
        // 自定义转换逻辑
        pageDTO.setList(records.stream().map(function).collect(Collectors.toList()));
        return pageDTO;
    }
}
