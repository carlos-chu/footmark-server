/**
 * Copyright (C) 2015 Creditease All rights reserved.
 */
package com.footmark.core.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.footmark.core.enums.MultiMediaTypeEnum;

public class MultiMediaTypeEnumHandler extends BaseTypeHandler<MultiMediaTypeEnum> implements
        TypeHandler<MultiMediaTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MultiMediaTypeEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public MultiMediaTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return code == null ? null : MultiMediaTypeEnum.toEnum(code);
    }

    @Override
    public MultiMediaTypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return code == null ? null : MultiMediaTypeEnum.toEnum(code);
    }

    @Override
    public MultiMediaTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return code == null ? null : MultiMediaTypeEnum.toEnum(code);
    }

}