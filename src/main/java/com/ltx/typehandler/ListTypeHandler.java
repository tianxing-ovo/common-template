package com.ltx.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List -> varchar
 */
public class ListTypeHandler extends BaseTypeHandler<List<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int index, List<String> list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(index, list.stream().distinct().sorted().collect(Collectors.joining(",")));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String column) throws SQLException {
        String value = resultSet.getString(column);
        if (value != null) {
            return Arrays.asList(value.split(","));
        }
        return null;
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int index) throws SQLException {
        String value = resultSet.getString(index);
        if (value != null) {
            return Arrays.asList(value.split(","));
        }
        return null;
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
        String value = callableStatement.getString(index);
        if (value != null) {
            return Arrays.asList(value.split(","));
        }
        return null;
    }
}
