package com.ltx.entity.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 分页查询实体
 *
 * @author tianxing
 */
@Data
public class PageQuery {
    // 页码
    private Integer pageNum = 1;
    // 每页数量
    private Integer pageSize = 10;
    // 排序字段
    private String sortField;
    // 升序还是降序(asc: 升序 desc: 降序)
    private String sortOrder = "asc";

    /**
     * 转换为分页对象
     *
     * @param items 排序字段
     * @return 分页对象
     */
    public Page<?> toPage(OrderItem... items) {
        Page<?> page = Page.of(pageNum, pageSize);
        if (StringUtils.isNotBlank(sortField)) {
            boolean asc = "asc".equalsIgnoreCase(sortOrder);
            page.addOrder(new OrderItem(sortField, asc));
        } else if (items != null) {
            // 默认排序
            page.addOrder(items);
        }
        return page;
    }

    /**
     * 转换为分页对象
     *
     * @param column 默认排序字段
     * @param asc    是否升序
     * @return 分页对象
     */
    public Page<?> toPage(String column, boolean asc) {
        return toPage(new OrderItem(column, asc));
    }
}
